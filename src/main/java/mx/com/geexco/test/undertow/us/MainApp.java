package mx.com.geexco.test.undertow.us;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.handlers.form.EagerFormParsingHandler;
import java.net.InetAddress;
import mx.com.geexco.test.undertow.us.handlers.NotificationHandler;
import mx.com.geexco.test.undertow.us.handlers.RegistrationHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author J12411
 */
public class MainApp {

    private static Logger log = LoggerFactory.getLogger("Main");

    public static void main(String[] args) throws Exception {
        String hostname = InetAddress.getLocalHost().getHostName();
        String port = System.getProperty("server.port");
        if (port == null) {
            port = "8080";
        }
        int puerto = Integer.valueOf(port);
        Undertow server = Undertow.builder().setWorkerThreads(10)
                .addHttpListener(puerto, hostname)
                .setHandler(Handlers.path()
                        .addExactPath("/api/register", new EagerFormParsingHandler().setNext(new RegistrationHandler()))
                        .addExactPath("/api/notify", new EagerFormParsingHandler().setNext(new NotificationHandler()))
                ).build();
        log.info("Server started on " + hostname + ":" + puerto);
        server.start();

    }

}
