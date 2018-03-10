
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

object personalTweetPage extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[Long,play.twirl.api.HtmlFormat.Appendable] {

  /* TEST */
  def apply/*9.2*/(id: Long):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](_display_(/*10.2*/main("Personal Page")/*10.23*/ {_display_(Seq[Any](format.raw/*10.25*/("""
    """),format.raw/*11.5*/("""<h1> User Info : """),_display_(/*11.23*/id),format.raw/*11.25*/("""</h1>
""")))}))
      }
    }
  }

  def render(id:Long): play.twirl.api.HtmlFormat.Appendable = apply(id)

  def f:((Long) => play.twirl.api.HtmlFormat.Appendable) = (id) => apply(id)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Fri Mar 09 22:23:14 EST 2018
                  SOURCE: /Users/uno/GitHub/playFrameWork/app/views/personalTweetPage.scala.html
                  HASH: 3b859060d9e6927821b8ab110b1bdff9f29ed99c
                  MATRIX: 964->122|1069->134|1099->155|1139->157|1171->162|1216->180|1239->182
                  LINES: 28->9|33->10|33->10|33->10|34->11|34->11|34->11
                  -- GENERATED --
              */
          