
package views.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import java.lang._
import java.util._
import scala.collection.JavaConverters._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import play.data._
import play.core.j.PlayFormsMagicForJava._
/*1.2*/import services.TweetAPI.TweetResult

object searchPage extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[String,List[TweetResult],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*2.2*/(keyWord: String)(tweetList: List[TweetResult]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
"""),_display_(/*4.2*/main("Search Tweet")/*4.22*/ {_display_(Seq[Any](format.raw/*4.24*/("""

    """),format.raw/*6.5*/("""<form>
        <input type="text" name="keyWord" formaction="searchPage" method="get">
        <br>
        <button type="submit">Search</button>
    </form>

    <ul>
        """),_display_(/*13.10*/for(tweet <- tweetList) yield /*13.33*/ {_display_(Seq[Any](format.raw/*13.35*/("""
            """),format.raw/*14.13*/("""<a href=""""),_display_(/*14.23*/routes/*14.29*/.TweetSearchController.getPersonalTweet(tweet.getUser_id)),format.raw/*14.86*/("""">
            <li>"""),_display_(/*15.18*/tweet/*15.23*/.getScreen_name),format.raw/*15.38*/("""</li>
            </a>
        """)))}),format.raw/*17.10*/("""
    """),format.raw/*18.5*/("""</ul>
""")))}))
      }
    }
  }

  def render(keyWord:String,tweetList:List[TweetResult]): play.twirl.api.HtmlFormat.Appendable = apply(keyWord)(tweetList)

  def f:((String) => (List[TweetResult]) => play.twirl.api.HtmlFormat.Appendable) = (keyWord) => (tweetList) => apply(keyWord)(tweetList)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Fri Mar 09 22:26:13 EST 2018
                  SOURCE: /Users/uno/GitHub/playFrameWork/app/views/searchPage.scala.html
                  HASH: 8cc32f59bf0c83bd45313e868064c155c2a8f2e2
                  MATRIX: 651->1|1015->39|1156->87|1183->89|1211->109|1250->111|1282->117|1486->294|1525->317|1565->319|1606->332|1643->342|1658->348|1736->405|1783->425|1797->430|1833->445|1896->477|1928->482
                  LINES: 24->1|29->2|34->3|35->4|35->4|35->4|37->6|44->13|44->13|44->13|45->14|45->14|45->14|45->14|46->15|46->15|46->15|48->17|49->18
                  -- GENERATED --
              */
          