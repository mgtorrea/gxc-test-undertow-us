package mx.com.geexco.test.undertow.us.handlers;

import com.google.gson.GsonBuilder;
import mx.com.geexco.test.undertow.us.dao.RegistrationSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author J12411
 */
public class ListaRegistradosHandler extends AbstractJSONProcessorHandler {

    Logger log = LoggerFactory.getLogger(this.getClass());
    RegistrationSource reg;

    public ListaRegistradosHandler(RegistrationSource reg) {
        this.reg = reg;
    }

    @Override
    public String handleJSONMessage(String msg) {
        return new GsonBuilder().create().toJson(reg.getMap());
    }

}
