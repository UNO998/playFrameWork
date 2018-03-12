package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import models.Actor;
import models.Twitter;
import models.User;
import play.data.Form;
import play.data.FormFactory;
import play.libs.concurrent.HttpExecutionContext;
import play.libs.oauth.OAuth;
import play.libs.oauth.OAuth.ConsumerKey;
import play.libs.oauth.OAuth.OAuthCalculator;
import play.libs.oauth.OAuth.RequestToken;
import play.libs.oauth.OAuth.ServiceInfo;
import play.libs.ws.WSClient;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.text;
import com.google.common.base.Strings;
import views.html.user;

import javax.inject.Inject;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;


public class TwitterController extends Controller {
    private HttpExecutionContext httpExecutionContext;


    @Inject
    FormFactory formFactory;

    static final ConsumerKey KEY = new ConsumerKey("M0YLKTvUOn94ppgkndCtNOzg8", "SLyd4eDSj7gQf9go2VVzTU1WQ8fxD78nngbDeUGF1z3h74Feak");

    private static final ServiceInfo SERVICE_INFO =
            new ServiceInfo("https://api.twitter.com/oauth/request_token",
                    "https://api.twitter.com/oauth/access_token",
                    "https://api.twitter.com/oauth/authorize",
                    KEY);

    private static final OAuth TWITTER = new OAuth(SERVICE_INFO);
    private final WSClient ws;
    private String hashtag = "";
    private String strUrl = "https://api.twitter.com/1.1/search/tweets.json?q=%20";
    List<Actor> actors = new LinkedList<>();
    Map<String, User> users = new HashMap<>();
    @Inject
    public TwitterController(WSClient ws, HttpExecutionContext ec) {
        this.ws = ws;
        this.httpExecutionContext = ec;
    }

    public Result refresh() {
        this.hashtag = "";
        actors.clear();
        return redirect(routes.TwitterController.getPage());
    }


    public Result save(){
        Form<Twitter> TitterForm = formFactory.form(Twitter.class).bindFromRequest();
        Twitter twitter = TitterForm.get();
        this.hashtag = twitter.hashtag;
        return redirect(routes.TwitterController.getPage());
    }

    public Result getPage() throws Exception{
        Form<Twitter> twitterForm = formFactory.form(Twitter.class);
        if (this.hashtag.length() == 0) {
            return ok(text.render(actors, twitterForm));
        }else return homeTimeline().toCompletableFuture().get();
    }

    public CompletionStage<Result> userInfo(String id) {
        List<String> texts = new LinkedList<>();
        Optional<RequestToken> sessionTokenPair = getSessionTokenPair();
        if (sessionTokenPair.isPresent()) {
            return ws.url("https://api.twitter.com/1.1/statuses/user_timeline.json?user_id=" + id + "&count=10")
                    .sign(new OAuthCalculator(TwitterController.KEY, sessionTokenPair.get()))
                    .get()
                    .thenApply(result -> {
                                JsonNode json = result.asJson();
                                ArrayNode arr = (ArrayNode)json;
                                Iterator<JsonNode> it = arr.iterator();
                                while (it.hasNext()) {
                                    JsonNode next = it.next();
                                    texts.add(next.findPath("text").asText());
                                }
                                users.get(id).setTexts(texts);
                                return ok(user.render(users.get(id)));
                            }
                    );
        }
        return CompletableFuture.completedFuture(redirect(routes.TwitterController.auth()));
    }


    public CompletionStage<Result> homeTimeline() {
        Form<Twitter> twitterForm = formFactory.form(Twitter.class);
        String url = strUrl + this.hashtag;
        Optional<RequestToken> sessionTokenPair = getSessionTokenPair();
        if (sessionTokenPair.isPresent()) {
            return ws.url(url)
                    .sign(new OAuthCalculator(TwitterController.KEY, sessionTokenPair.get()))
                    .get()
                    .thenApplyAsync(result -> {
                        JsonNode json = result.asJson();
                        JsonNode node = json.findPath("statuses");
                        ArrayNode arr = (ArrayNode)node;
                        Iterator<JsonNode> it = arr.iterator();
                        List<Actor> tempActor = new LinkedList<>();
                        while (it.hasNext()) {
                            JsonNode next = it.next();
                            JsonNode user = next.findPath("user");
                            String user_id = user.findPath("id").asText();
                            if (users.get(user_id) == null) {
                                users.put(user_id,
                                        new User(user_id,
                                                user.findPath("name").asText(),
                                                user.findPath("screen_name").asText(),
                                                user.findPath("description").asText(),
                                                user.findPath("profile_image_url_https").asText(),
                                                user.findPath("followers_count").asInt(),
                                                user.findPath("listed_count").asInt(),
                                                user.findPath("friends_count").asInt(),
                                                user.findPath("created_at").asText()));
                            }
                            tempActor.add(new Actor(next.findPath("text").asText(), users.get(user_id)));
                        }
                        actors.addAll(tempActor.stream().limit(10).collect(Collectors.toList()));
                        this.hashtag = "";
                        return ok(text.render(actors, twitterForm));
                    }, httpExecutionContext.current());
        }
        return CompletableFuture.completedFuture(redirect(routes.TwitterController.auth()));
    }

    public Result auth() {
        String verifier = request().getQueryString("oauth_verifier");
        if (Strings.isNullOrEmpty(verifier)) {
            String url = routes.TwitterController.auth().absoluteURL(request());
            RequestToken requestToken = TWITTER.retrieveRequestToken(url);
            saveSessionTokenPair(requestToken);
            return redirect(TWITTER.redirectUrl(requestToken.token));
        } else {
            RequestToken requestToken = getSessionTokenPair().get();
            RequestToken accessToken = TWITTER.retrieveAccessToken(requestToken, verifier);
            saveSessionTokenPair(accessToken);

            return redirect(routes.TwitterController.getPage());
        }
    }

    private void saveSessionTokenPair(RequestToken requestToken) {
        System.out.println(requestToken.token);
        session("token", requestToken.token);
        session("secret", requestToken.secret);
    }

    private Optional<RequestToken> getSessionTokenPair() {
        if (session().containsKey("token")) {
            return Optional.ofNullable(new RequestToken(session("token"), session("secret")));
        }
        return Optional.empty();
    }

}