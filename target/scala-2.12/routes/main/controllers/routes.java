// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/uno/GitHub/playFrameWork/conf/routes
// @DATE:Fri Mar 09 22:53:07 EST 2018

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseTweetSearchController TweetSearchController = new controllers.ReverseTweetSearchController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseAssets Assets = new controllers.ReverseAssets(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseTweetSearchController TweetSearchController = new controllers.javascript.ReverseTweetSearchController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseAssets Assets = new controllers.javascript.ReverseAssets(RoutesPrefix.byNamePrefix());
  }

}
