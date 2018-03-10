package controllers;


import play.mvc.Controller;
import play.mvc.Result;
import services.TweetAPI.Fecther;
import services.TweetAPI.TweetResult;
import views.html.searchPage;

import java.util.List;


public class SearchController extends Controller {

    private final Fecther fecther;

    public SearchController() {this.fecther = new Fecther();}

    /**
     * get keyword and render 10 tweets to searchPage.scala.htmlhtml
     */
    public Result searchByKeyWord(String keyWord) {
        try {
            List<TweetResult> searchResult = fecther.getSearchTweet(keyWord);
            return ok(searchPage.render(keyWord, searchResult));
        }catch(Exception e) {
            System.out.println(e.toString());
            return ok("Exception");
        }
    }

    /**
     *redirect to userPersonal Page
     */
//    public Result getPersonalTweet(int id) {
//        try {
//            UserInfo userInfo = fecther.getInfoById(id);
//            return ok(personalPage.render(userInfo));
//        }catch(Exception e) {
//            System.out.println(e.toString());
//            return ok("Exception");
//        }
//    }

//
//    /**
//     * An action that renders an HTML page with a welcome message.
//     * The configuration in the <code>routes</code> file means that
//     * this method will be called when the application receives a
//     * <code>GET</code> request with a path of <code>/</code>.
//     */
//    public Result searchTweet() throws Exception{
//        List<TweetResult> tweetResults = fecther.getSearchTweet("hello");
//        ObjectNode result = Json.newObject();
//        for (int i = 0; i < tweetResults.size(); ++i) {
//            result.put(i + "", tweetResults.get(i).toString());
////            System.out.println(tweetResults.get(i));
//        }
//        return ok(result);
//    }
}
