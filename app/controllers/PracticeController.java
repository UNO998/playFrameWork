package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.util.Arrays;
import java.util.List;

/**
 * this Controller is for practicing
 */
public class PracticeController extends Controller{


    //none-parameter controller
    public Result index() {
        return ok("It works!");
    }

    //parameter-included controller
    public Result index2(String name){

        //setting and discarding cookies
        //response().setCookie(Http.Cookie.builder("name", "yiranshen").build());

        return ok("Hello" + name);

        /*
            Result type :
                ok("Hello World");
                notFound();
                notFound("<h1>Page not found</h1>".as("text/html");
                badRequest(views.html.form.render(formWithErrors));
                ...
         */

    }

    //redirect to other action : index2
    public Result index3(String name, int age) {
        return redirect(controllers.routes.PracticeController.index2(name+", " +age));
    }


    //action for rendering practice.scala.html
    public Result index4() {
        String customer = "yiran shen";
        List<String> orders = Arrays.asList("xiaomi", "damai", "doujiang");

        return ok(practice.render(customer, orders));
    }
    //if you want to change the type of result .
    // using :  Result htmleResult = ok("<h1>asdf</h1>.as("text/html")"

}
