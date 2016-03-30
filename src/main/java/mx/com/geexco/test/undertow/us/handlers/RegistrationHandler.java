package mx.com.geexco.test.undertow.us.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author J12411
 */
public class RegistrationHandler extends AbstractJSONProcessorHandler{

    Logger log= LoggerFactory.getLogger(this.getClass());

    @Override
    public String handleJSONMessage(String msg) {
        return "Hola mundo";
    }
    
}
