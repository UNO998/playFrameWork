package controllers;

import models.Book;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
//import twitter4j.*;
//import twitter4j.conf.ConfigurationBuilder;
import views.html.book.create;
import views.html.book.edit;
import views.html.book.index;
import views.html.book.show;

import javax.inject.Inject;
import java.util.List;
import java.util.Set;

public class BooksController extends Controller{

    @Inject
    FormFactory formFactory;

    public Result index(){
        List<Book> books = Book.find.all();
        return ok(index.render(books));
    }

    public Result create(){
        Form<Book> bookForm = formFactory.form(Book.class);
        return ok(create.render(bookForm));
    }

    public Result save(){
        Form<Book> bookForm = formFactory.form(Book.class).bindFromRequest();
        Book book = bookForm.get();
//        return ok(book.author);
        book.save();
        return redirect(routes.BooksController.index());
    }

    public Result edit(Integer id){
        Book book = Book.find.byId(id);
        if(book == null){
            return notFound("Book not found");
        }
        Form<Book> bookForm = formFactory.form(Book.class).fill(book);
        return ok(edit.render(bookForm));
    }

    public Result update(){
        Book book = formFactory.form(Book.class).bindFromRequest().get();
        Book oldBook = Book.find.byId(book.id);
        if(oldBook == null){
            return notFound("book not found");
        }
        oldBook.title = book.title;
        oldBook.author = book.author;
        oldBook.price = book.price;
        oldBook.update();

        return redirect(routes.BooksController.index());
    }

    public Result destroy(Integer id){
        Book book = Book.find.byId(id);
        if(book == null){
            return notFound("Book not found");
        }
        book.delete();

        return redirect(routes.BooksController.index());
    }

    public Result show(Integer id){
        Book book = Book.find.byId(id);
        if(book == null){
            return notFound("Book not found");
        }


        return ok(show.render(book));
    }
}
