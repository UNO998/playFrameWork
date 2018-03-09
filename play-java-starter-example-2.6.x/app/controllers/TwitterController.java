package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import play.Logger;
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

import javax.inject.Inject;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class TwitterController extends Controller {

    static final ConsumerKey KEY = new ConsumerKey("M0YLKTvUOn94ppgkndCtNOzg8", "SLyd4eDSj7gQf9go2VVzTU1WQ8fxD78nngbDeUGF1z3h74Feak");

    private static final ServiceInfo SERVICE_INFO =
            new ServiceInfo("https://api.twitter.com/oauth/request_token",
                    "https://api.twitter.com/oauth/access_token",
                    "https://api.twitter.com/oauth/authorize",
                    KEY);

    private static final OAuth TWITTER = new OAuth(SERVICE_INFO);

    private final WSClient ws;

    private String strUrl = "https://api.twitter.com/1.1/search/tweets.json?q=%20new";

    List<String> descriptions = new LinkedList<>();

    @Inject
    public TwitterController(WSClient ws) {
        this.ws = ws;
    }


    public CompletionStage<Result> homeTimeline() {
        Logger.info(strUrl);
        strUrl += "";
        Optional<RequestToken> sessionTokenPair = getSessionTokenPair();
        if (sessionTokenPair.isPresent()) {
            return ws.url(strUrl)
                    .sign(new OAuthCalculator(TwitterController.KEY, sessionTokenPair.get()))
                    .get()
                    .thenApply(result -> {JsonNode json = result.asJson();
                        JsonNode node = json.findPath("statuses");
                        ArrayNode arr = (ArrayNode)node;
                        Iterator<JsonNode> it = arr.iterator();
                        while (it.hasNext()) {
                            JsonNode next = it.next();
                            descriptions.add(next.findPath("text").asText());
                        }
                        return ok(text.render(descriptions));
                    });
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

            return redirect(routes.TwitterController.homeTimeline());
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