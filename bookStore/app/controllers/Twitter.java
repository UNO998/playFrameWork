package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import controllers.routes;
import models.Book;
import models.KeyWord;
import models.WordTwitter;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.libs.oauth.OAuth;
import play.libs.oauth.OAuth.ConsumerKey;
import play.libs.oauth.OAuth.OAuthCalculator;
import play.libs.oauth.OAuth.RequestToken;
import play.libs.oauth.OAuth.ServiceInfo;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;
import play.mvc.Controller;
import play.mvc.Result;

import com.google.common.base.Strings;
import views.html.book.TwitterIndex;
import views.html.book.TwitterSearch;
import views.html.book.index;
import views.html.book.search;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class Twitter extends Controller {
    static final ConsumerKey KEY = new ConsumerKey("80CruhaMQhCXPCcMKf20m0HQh", "LyjlgerJ4Vp4vWoOhUddxMfDo4upBH3SBHp3rpvok3B6FtZoYX");

    private static final ServiceInfo SERVICE_INFO =
            new ServiceInfo("https://api.twitter.com/oauth/request_token",
                    "https://api.twitter.com/oauth/access_token",
                    "https://api.twitter.com/oauth/authorize",
                    KEY);

    private static final OAuth TWITTER = new OAuth(SERVICE_INFO);

    private final WSClient ws;

    @Inject
    FormFactory formFactory;

    @Inject
    public Twitter(WSClient ws) {
        this.ws = ws;
    }

    public Result index(){
        List<WordTwitter> twitters = WordTwitter.alltweets();
        return ok(TwitterIndex.render(twitters));
    }

    public Result search() {
        Form<KeyWord> keyWordForm = formFactory.form(KeyWord.class);
        return ok(TwitterSearch.render(keyWordForm));
    }

    public CompletionStage<Result> homeTimeline() {
        Form<KeyWord> wordfrom = formFactory.form(KeyWord.class).bindFromRequest();
        KeyWord keyword = wordfrom.get();
        String word = keyword.keyword;

        Optional<RequestToken> sessionTokenPair = getSessionTokenPair();
        if (sessionTokenPair.isPresent()) {
//            CompletionStage<Result> json =  ws.url("https://api.twitter.com/1.1/statuses/home_timeline.json")
            return ws.url("https://api.twitter.com/1.1/search/tweets.json?q=%23" + word + "&count=10")
                    .sign(new OAuthCalculator(Twitter.KEY, sessionTokenPair.get()))
                    .get()
                    .thenApply(result -> {
                        String value = "";
                        JsonNode j = result.asJson().findPath("statuses");
                        for (JsonNode js :
                                j) {
                            WordTwitter wordTwitter = new WordTwitter(
                                    js.findPath("user").findPath("name").toString(),
                                    js.findPath("text").toString(),
                                    js.findPath("user").findPath("id_str").toString());
                            WordTwitter.add(wordTwitter);
                            value = value + "text:" + js.findPath("text") + "\n";
                        }

                        return redirect(routes.Twitter.index());
                    });

        }
        return CompletableFuture.completedFuture(redirect(routes.Twitter.auth()));
    }

    public CompletionStage<Result> userTimeLine(String id){
        Optional<RequestToken> sessionTokenPair = getSessionTokenPair();
        if(sessionTokenPair.isPresent()){
            return ws.url("https://api.twitter.com/1.1/statuses/user_timeline.json?user_id="+ id +"&count=10")
                    .sign(new OAuthCalculator(Twitter.KEY, sessionTokenPair.get()))
                    .get()
                    .thenApply(result -> {
                        String str = "";
                        JsonNode json = result.asJson();
                        for (JsonNode j :
                                json) {
                            str = str + "text:" + json.findPath("text") + "\n";
                        }
                        return ok(result.asJson());
                    });
        }
        return CompletableFuture.completedFuture(redirect(routes.Twitter.auth()));
    }

    public Result auth() {
        String verifier = request().getQueryString("oauth_verifier");
        if (Strings.isNullOrEmpty(verifier)) {
            String url = routes.Twitter.auth().absoluteURL(request());
            RequestToken requestToken = TWITTER.retrieveRequestToken(url);
            saveSessionTokenPair(requestToken);
            return redirect(TWITTER.redirectUrl(requestToken.token));
        } else {
            RequestToken requestToken = getSessionTokenPair().get();
            RequestToken accessToken = TWITTER.retrieveAccessToken(requestToken, verifier);
            saveSessionTokenPair(accessToken);
            return redirect(routes.Twitter.homeTimeline());
        }
    }

    private void saveSessionTokenPair(RequestToken requestToken) {
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

