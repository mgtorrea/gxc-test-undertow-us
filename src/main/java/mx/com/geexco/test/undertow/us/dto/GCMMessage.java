package mx.com.geexco.test.undertow.us.dto;

import com.google.gson.annotations.SerializedName;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author J12411
 */
public class GCMMessage {

    @SerializedName(value = "registration_ids")
    private String[] receivers;
    @SerializedName(value = "data")
    private Map<String, String> payload = new HashMap<>();

    public GCMMessage() {
    }

    public void addData(String key, String value) {
        payload.put(key, value);
    }

    public GCMMessage(String[] receivers) {
        this.receivers = receivers;
    }

    public String[] getReceivers() {
        return receivers;
    }

    public void setReceivers(String[] receivers) {
        this.receivers = receivers;
    }

    public Map<String, String> getPayload() {
        return payload;
    }

    public void setPayload(Map<String, String> payload) {
        this.payload = payload;
    }

}
