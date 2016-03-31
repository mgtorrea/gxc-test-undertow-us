package mx.com.geexco.test.undertow.us.handlers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import mx.com.geexco.test.undertow.us.dao.RegistrationSource;
import mx.com.geexco.test.undertow.us.dto.RegistrationDTO;
import mx.com.geexco.test.undertow.us.dto.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author J12411
 */
public class RegistrationHandler extends AbstractJSONProcessorHandler {

    Logger log = LoggerFactory.getLogger(this.getClass());
    RegistrationSource reg;

    public RegistrationHandler(RegistrationSource reg) {
        this.reg = reg;
    }

    @Override
    public String handleJSONMessage(String msg) {
        Gson gson = new GsonBuilder().create();
        if (msg.isEmpty()) {
            return gson.toJson(new ResponseDTO(-1));
        }
        RegistrationDTO r;
        try {
            r = gson.fromJson(msg, RegistrationDTO.class);
        } catch (Exception e) {
            log.info("Error getting DTO from '" + msg + "'");
            return gson.toJson(new ResponseDTO(-2));
        }
        reg.register(r);
        return gson.toJson(new ResponseDTO(0));
    }

}
