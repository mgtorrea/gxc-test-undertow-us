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

    public Map getMap(){
        return this.map;
    }

    public String []getDeviceIds(String[] ids) {
        List devices=new ArrayList<String>();
        for(String id:ids){
            String dev=map.get(id);
            if(dev!=null){
                devices.add(dev);
            }
        }
        return devices.toArray();
    }

}
