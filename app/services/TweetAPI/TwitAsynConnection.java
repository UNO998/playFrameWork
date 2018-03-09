package services.TweetAPI;

import twitter4j.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class TwitAsynConnection implements AsynConnection{
    private Twitter twitter;

    protected  TwitAsynConnection(Twitter t){
        twitter = t;
    }



    @Override
    public CompletableFuture<List<TweetResult>> SearchTweet(String keyword, int max_cnt) {
        CompletableFuture<List<TweetResult>> ret = new CompletableFuture<>();
        final int finalMax_cnt;
        if (max_cnt < 0)
            finalMax_cnt = Integer.MAX_VALUE;
        else
            finalMax_cnt = max_cnt;

        new Thread(() -> {
            ArrayList<TweetResult> tweetResults = new ArrayList<>();
            Query query = new Query(keyword);
            try {
                QueryResult q_result = twitter.search(query);
                List<Status> statuses = q_result.getTweets();

                int i = 0;
                for (Status status : statuses) {
                    TweetResult tweetResult = new TweetResult(status.getUser().getName(), status.getUser().getId());
                    tweetResult.setScreen_name(status.getUser().getScreenName());
                    tweetResult.setHyperlink(status.getUser().getURL());
                    tweetResult.setText(status.getText());

                    tweetResults.add(tweetResult);
                    if (++i == finalMax_cnt)
                        break;
                }
            } catch (TwitterException e) {
                e.printStackTrace();
            }

            ret.complete(tweetResults);
        }).start();

        return ret;
    }

    @Override
    public CompletableFuture<List<TweetResult>> getHomeLine() {
        CompletableFuture<List<TweetResult>> ret = new CompletableFuture<>();

        new Thread( () -> {
            ArrayList<TweetResult> tweetResults = new ArrayList<>();

            try {
                List<Status> statues = twitter.getHomeTimeline();
                for(Status status : statues){
                    TweetResult tweetResult = new TweetResult(status.getUser().getName(), status.getUser().getId());
                    tweetResult.setScreen_name(status.getUser().getScreenName());
                    tweetResult.setHyperlink(status.getUser().getURL());
                    tweetResult.setText(status.getText());
                }

            } catch (TwitterException e) {
                e.printStackTrace();
            }

            ret.complete(tweetResults);
        }).start();

        return ret;
    }

    @Override
    public CompletableFuture<List<TweetResult>> SearchTweet(String keyword) {
        return SearchTweet(keyword, -1);
    }

    public CompletableFuture<List<TweetResult>> getUserLatestTweet(long user_id){
        CompletableFuture<List<TweetResult>> ret = new CompletableFuture<>();

        new Thread( () -> {
            ArrayList<TweetResult> tweetResults = new ArrayList<>();

            try {
                List<Status> statues = twitter.getUserTimeline(user_id);
                for(Status status : statues){
                    TweetResult tweetResult = new TweetResult(status.getUser().getName(), status.getUser().getId());
                    tweetResult.setScreen_name(status.getUser().getScreenName());
                    tweetResult.setHyperlink(status.getUser().getURL());
                    tweetResult.setText(status.getText());
                }
            } catch (TwitterException e) {
                e.printStackTrace();
            }

            ret.complete(tweetResults);
        }).start();

        return ret;
    }


}
