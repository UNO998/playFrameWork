import org.junit.Test;
import play.test.WithApplication;
import play.twirl.api.Content;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A functional test starts a Play application for every test.
 *
 * https://www.playframework.com/documentation/latest/JavaFunctionalTest
 */
public class FunctionalTest extends WithApplication {

    @Test
    public void renderTemplate() {
        Content html = views.html.index.render();
        assertThat("text/html").isEqualTo(html.contentType());
        assertThat(html.body()).contains("Your new application is ready.");
    }
}
