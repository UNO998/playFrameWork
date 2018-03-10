// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/uno/GitHub/playFrameWork/conf/routes
// @DATE:Fri Mar 09 22:53:07 EST 2018

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:6
  Assets_0: controllers.Assets,
  // @LINE:9
  TweetSearchController_1: controllers.TweetSearchController,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:6
    Assets_0: controllers.Assets,
    // @LINE:9
    TweetSearchController_1: controllers.TweetSearchController
  ) = this(errorHandler, Assets_0, TweetSearchController_1, "/")

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, Assets_0, TweetSearchController_1, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """searchPage""", """controllers.TweetSearchController.searchByKeyWord(keyWord:String ?= "hello")"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """personalTweetPage""", """controllers.TweetSearchController.getPersonalTweet(id:Long)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:6
  private[this] lazy val controllers_Assets_versioned0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned0_invoker = createInvoker(
    Assets_0.versioned(fakeValue[String], fakeValue[Asset]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )

  // @LINE:9
  private[this] lazy val controllers_TweetSearchController_searchByKeyWord1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("searchPage")))
  )
  private[this] lazy val controllers_TweetSearchController_searchByKeyWord1_invoker = createInvoker(
    TweetSearchController_1.searchByKeyWord(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.TweetSearchController",
      "searchByKeyWord",
      Seq(classOf[String]),
      "GET",
      this.prefix + """searchPage""",
      """ get keyWord from searchPage form and pass to TweetSearchController""",
      Seq()
    )
  )

  // @LINE:12
  private[this] lazy val controllers_TweetSearchController_getPersonalTweet2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("personalTweetPage")))
  )
  private[this] lazy val controllers_TweetSearchController_getPersonalTweet2_invoker = createInvoker(
    TweetSearchController_1.getPersonalTweet(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.TweetSearchController",
      "getPersonalTweet",
      Seq(classOf[Long]),
      "GET",
      this.prefix + """personalTweetPage""",
      """ get #user_id from user click and route to TweetSearchController""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:6
    case controllers_Assets_versioned0_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned0_invoker.call(Assets_0.versioned(path, file))
      }
  
    // @LINE:9
    case controllers_TweetSearchController_searchByKeyWord1_route(params@_) =>
      call(params.fromQuery[String]("keyWord", Some("hello"))) { (keyWord) =>
        controllers_TweetSearchController_searchByKeyWord1_invoker.call(TweetSearchController_1.searchByKeyWord(keyWord))
      }
  
    // @LINE:12
    case controllers_TweetSearchController_getPersonalTweet2_route(params@_) =>
      call(params.fromQuery[Long]("id", None)) { (id) =>
        controllers_TweetSearchController_getPersonalTweet2_invoker.call(TweetSearchController_1.getPersonalTweet(id))
      }
  }
}
