package mx.com.geexco.test.undertow.us.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import mx.com.geexco.test.undertow.us.dto.RegistrationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author gxc-mg
 */
public class RegistrationSource {
    
    Logger log = LoggerFactory.getLogger(this.getClass());

    private final Map<String, String> map = new HashMap<>();

    public void register(RegistrationDTO reg) {
        map.put(reg.getId(), reg.getDevice());
    }

    public Map getMap(){
        return this.map;
    }

    public String []getDeviceIds(String[] ids) {
        List<String> devices=new ArrayList<String>();
        for(String id:ids){
            log.info(id);
            String dev=map.get(id);
            log.info(">>>"+dev);
            if(dev!=null){
                devices.add(dev);
            }
        }
        return (String[])devices.toArray();
    }

}
