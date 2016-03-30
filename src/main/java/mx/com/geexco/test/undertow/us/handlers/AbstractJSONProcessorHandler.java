package mx.com.geexco.test.undertow.us.handlers;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author J12411
 */
public abstract class AbstractJSONProcessorHandler implements HttpHandler {

    public abstract String handleJSONMessage(String msg);
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void handleRequest(HttpServerExchange hse) throws Exception {
        if (hse.isInIoThread()) {
            hse.dispatch(this);
            return;
        }
        //Obtiene entrada POST
        String data = "";
        String res = handleJSONMessage(data);
        byte[] b = res.getBytes();
        //hse.getResponseHeaders().put(Headers.CONTENT_DISPOSITION, "attachment; filename=output.json");
        hse.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
        hse.setResponseContentLength(b.length);
        hse.startBlocking();
        hse.getOutputStream().write(b);
        //bos.writeTo(hse.getOutputStream());
        hse.endExchange();
        hse.unDispatch();
    }

}
