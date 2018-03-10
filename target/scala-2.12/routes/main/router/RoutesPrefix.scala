// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/uno/GitHub/playFrameWork/conf/routes
// @DATE:Fri Mar 09 21:33:52 EST 2018


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
