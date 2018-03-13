import org.junit.Test;
import play.mvc.Http.RequestBuilder;
import play.mvc.Result;
import play.test.Helpers;

import static junit.framework.TestCase.assertEquals;
import static play.mvc.Http.HttpVerbs.GET;
import static play.shaded.ahc.io.netty.handler.codec.rtsp.RtspResponseStatuses.NOT_FOUND;
import static play.test.Helpers.route;

public class FunctionalTest {

    @Test
    public void testBadRoute() {
        RequestBuilder request = Helpers.fakeRequest()
                .method(GET)
                .uri("/twitter/getPage");

        Result result = route(app, request);
        assertEquals(NOT_FOUND, result.status());
    }
}
