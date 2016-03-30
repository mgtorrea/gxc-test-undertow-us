package mx.com.geexco.test.undertow.us.handlers;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author J12411
 */
public class NotificationHandler extends AbstractJSONProcessorHandler {

    Logger log = LoggerFactory.getLogger(this.getClass());
    private String apiKey = "";

    private void notifica(String registrationId, String msgContent) {
        Sender sender = new Sender(apiKey);
        Message message = new Message.Builder()
                .addData("msg", msgContent)
                .build();
        try {
            Result result = sender.send(message, registrationId, 0);
        } catch (IOException ex) {
            log.error("Error al enviar la notificacion: ", ex);
        }

    }

    @Override
    public String handleJSONMessage(String msg) {
        return "NOTIFICA!";
    }

}
