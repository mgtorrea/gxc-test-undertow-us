package mx.com.geexco.test.undertow.us.handlers;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author J12411
 */
public abstract class AbstractJSONProcessorHandler implements HttpHandler {

    public abstract String handleJSONMessage(String msg);
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    ByteArrayOutputStream baos = new ByteArrayOutputStream(1024 * 5);
    StringBuffer sb = new StringBuffer();

    @Override
    public synchronized void handleRequest(HttpServerExchange hse) throws Exception { //TODO ESTE ELEMENTO SYNCRHONIZED DEBERIA DEPRECIARSE CUANDO EL OBJETO UTILIZADO NO SEA SINGLETON.
        if (hse.isInIoThread()) {
            hse.dispatch(this);
            return;
        }
        //Obtiene entrada POST
        String data = "";
        hse.startBlocking();
        IOUtils.copy(hse.getInputStream(), baos);
        data = baos.toString("UTF-8");
        baos.reset();
        String res = handleJSONMessage(data);
        byte[] b = res.getBytes();
        //hse.getResponseHeaders().put(Headers.CONTENT_DISPOSITION, "attachment; filename=output.json");
        hse.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
        hse.setResponseContentLength(b.length);

        hse.getOutputStream().write(b);
        //bos.writeTo(hse.getOutputStream());
        hse.endExchange();
        hse.unDispatch();
    }

}
