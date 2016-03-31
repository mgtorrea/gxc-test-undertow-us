package mx.com.geexco.test.undertow.us.dao;

import java.util.HashMap;
import java.util.Map;
import mx.com.geexco.test.undertow.us.dto.RegistrationDTO;

/**
 *
 * @author gxc-mg
 */
public class RegistrationSource {

    private final Map<String, String> map = new HashMap<>();

    public void register(RegistrationDTO reg) {
        map.put(reg.getId(), reg.getDevice());
    }

    public void list() {
        for (String key : map.keySet()) {
            System.out.println(key + "=>" + map.get(key));
        }
    }

    public String getDevice(String id) {
        return map.get(id);
    }

}
