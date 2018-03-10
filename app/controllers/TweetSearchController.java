package controllers;

import play.mvc.*;
import services.TweetAPI.Fecther;
import services.TweetAPI.TweetResult;
import views.html.*;
import javax.inject.Inject;
import java.util.List;


public class TweetSearchController extends Controller {

    private final Fecther fecther;

    @Inject
    public TweetSearchController() {this.fecther = new Fecther();}

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
     * template action for test
     * @param id
     * @return
     */
    public Result getPersonalTweet(Long id) {
        try {
            // @TEST
            return ok(personalTweetPage.render(id));
        }catch(Exception e) {
            System.out.println(e.toString());
            return ok("Exception");
        }
    }

}
