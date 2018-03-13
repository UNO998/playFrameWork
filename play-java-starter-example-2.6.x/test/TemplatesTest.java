import com.fasterxml.jackson.databind.JsonNode;
import models.Actor;
import models.Twitter;
import models.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.Configuration;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.test.WithApplication;
import play.test.WithBrowser;
import play.twirl.api.Content;
import play.twirl.api.Html;

import javax.inject.Inject;
import javax.swing.text.html.HTMLDocument;
import javax.xml.bind.Binder;
import java.util.List;

import static com.oracle.jrockit.jfr.Transition.From;
import static org.junit.Assert.*;
import static play.test.Helpers.contentAsString;
import static org.mockito.Mockito.*;

public class TemplatesTest extends WithBrowser {
    @Inject
    FormFactory formFactory;

    @Before
    public void configure(Binder binder){

    }
    @Test
    public void userTemplateTest() {
        User mockedUser = mock(User.class);
        Content html = views.html.user.render(mockedUser);
        assertEquals("text/html", html.contentType());
        assertTrue(contentAsString(html).contains("Recent Activity"));
    }

    @Test
    public void textTemplateTest(){

        Form<Twitter> form = formFactory.form(Twitter.class);

        List<Actor> mockedActor = mock(List.class);

        Content html = views.html.text.render(mockedActor ,form);
        assertEquals("text/html", html.contentType());
    }

    @Test
    public void layoutTemplateTest(){
        Content html = views.html.layout.render(new String(), new Html(""));
        assertEquals("text/html", html.contentType());
    }
}
