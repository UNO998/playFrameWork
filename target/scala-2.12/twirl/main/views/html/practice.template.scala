
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

object practice extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[String,List[String],play.twirl.api.HtmlFormat.Appendable] {

  /*
*    this is a scala html file for practice
*/
  def apply/*6.6*/(customer: String, orders: List[String]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*7.1*/("""
    """),format.raw/*8.5*/("""<!DOCTYPE html>
    <html lang="en">
        <head>
            """),format.raw/*11.66*/("""
            """),format.raw/*12.13*/("""<title>practice</title>
            <link rel="stylesheet" media="screen" href=""""),_display_(/*13.58*/routes/*13.64*/.Assets.versioned("stylesheets/main.css")),format.raw/*13.105*/("""">
            <link rel="shortcut icon" type="image/png" href=""""),_display_(/*14.63*/routes/*14.69*/.Assets.versioned("images/favicon.png")),format.raw/*14.108*/("""">
            <script src=""""),_display_(/*15.27*/routes/*15.33*/.Assets.versioned("javascripts/hello.js")),format.raw/*15.74*/("""" type="text/javascript"></script>
        </head>
        <body>
            """),format.raw/*19.35*/("""
            """),format.raw/*20.13*/("""<h1>this is data for """),_display_(/*20.35*/customer),format.raw/*20.43*/("""</h1>

            <ul>
            """),_display_(/*23.14*/for(order <- orders) yield /*23.34*/ {_display_(Seq[Any](format.raw/*23.36*/("""
                """),format.raw/*24.17*/("""<li>"""),_display_(/*24.22*/order),format.raw/*24.27*/("""</li>
            """)))}),format.raw/*25.14*/("""
            """),format.raw/*26.13*/("""</ul>

        </body>
    </html>
"""))
      }
    }
  }

  def render(customer:String,orders:List[String]): play.twirl.api.HtmlFormat.Appendable = apply(customer,orders)

  def f:((String,List[String]) => play.twirl.api.HtmlFormat.Appendable) = (customer,orders) => apply(customer,orders)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Fri Mar 09 17:13:10 EST 2018
                  SOURCE: /Users/uno/GitHub/playFrameWork/app/views/practice.scala.html
                  HASH: d5c8eb568b45ce5b6334c123d8b3262952da7ae9
                  MATRIX: 1009->57|1143->98|1174->103|1266->220|1307->233|1415->314|1430->320|1493->361|1585->426|1600->432|1661->471|1717->500|1732->506|1794->547|1900->718|1941->731|1990->753|2019->761|2083->798|2119->818|2159->820|2204->837|2236->842|2262->847|2312->866|2353->879
                  LINES: 30->6|35->7|36->8|39->11|40->12|41->13|41->13|41->13|42->14|42->14|42->14|43->15|43->15|43->15|46->19|47->20|47->20|47->20|50->23|50->23|50->23|51->24|51->24|51->24|52->25|53->26
                  -- GENERATED --
              */
          