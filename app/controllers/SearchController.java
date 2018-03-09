package controllers;


import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.TweetAPI.Fecther;
import services.TweetAPI.TweetResult;

import javax.inject.Inject;
import java.util.List;


public class SearchController extends Controller {

    private final Fecther fecther;

    @Inject
    public SearchController() {this.fecther = new Fecther();}


    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result searchTweet() throws Exception{
        List<TweetResult> tweetResults = fecther.getSearchTweet("hello");
        ObjectNode result = Json.newObject();
        for (int i = 0; i < tweetResults.size(); ++i) {
            result.put(i + "", tweetResults.get(i).toString());
//            System.out.println(tweetResults.get(i));
        }
        return ok(result);
    }
}
