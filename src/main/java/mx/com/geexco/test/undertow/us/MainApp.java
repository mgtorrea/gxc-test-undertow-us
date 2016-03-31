package mx.com.geexco.test.undertow.us;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.handlers.form.EagerFormParsingHandler;
import java.net.InetAddress;
import mx.com.geexco.test.undertow.us.dao.RegistrationSource;
import mx.com.geexco.test.undertow.us.handlers.NotificationHandler;
import mx.com.geexco.test.undertow.us.handlers.RegistrationHandler;
import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author gxc-mg
 */
public class MainApp {
    
    private static Logger log = LoggerFactory.getLogger("Main");
    
    public static void main(String[] args) {
        log.info("INITIALIZING");
        String hostname = "";
        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (Exception ex) {
            log.error("Error on getLocalhost", ex);
            return;
        }
        String port = System.getProperty("server.port");
        log.info("System port: " + port);
        if (port == null) {
            port = "8080";
        }
        int puerto = Integer.valueOf(port);
        RegistrationSource reg = new RegistrationSource();
        String serviceKey = System.getProperty("GCM_KEY");
        if (serviceKey == null) {
            log.error("Service cannot start without a serviceKey");
            return;
        }
        log.info("serviceKey: ****" + serviceKey.substring(serviceKey.length() - 8));
        Undertow server = Undertow.builder().setWorkerThreads(10)
                .addHttpListener(puerto, hostname)
                .setHandler(Handlers.path()
                        .addExactPath("/api/register", new EagerFormParsingHandler().setNext(new RegistrationHandler(reg)))
                        .addExactPath("/api/notify", new EagerFormParsingHandler().setNext(new NotificationHandler(reg, serviceKey)))
                ).build();
        log.info("Server started on " + hostname + ":" + puerto);
        server.start();
        
    }
    
}
