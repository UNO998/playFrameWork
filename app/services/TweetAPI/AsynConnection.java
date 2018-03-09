package services.TweetAPI;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface AsynConnection {
    public CompletableFuture<List<TweetResult>> SearchTweet(String keyword);
    public CompletableFuture<List<TweetResult>> SearchTweet(String keyword, int max_cnt);
    public CompletableFuture<List<TweetResult>> getHomeLine();
}

