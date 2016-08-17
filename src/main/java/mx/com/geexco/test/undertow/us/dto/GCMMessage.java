package mx.com.geexco.test.undertow.us.dto;

import com.google.gson.annotations.SerializedName;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author gxc-mg
 */
public class GCMMessage {

    @SerializedName(value = "registration_ids")
    private String[] receivers;
    @SerializedName(value = "data")
    private Map<String, String> payload = new HashMap<>();
    @SerializedName(value = "content-available")
    private int content_available;

    public GCMMessage() {
        this.content_available=1;
    }

    public void addData(String key, String value) {
        payload.put(key, value);
    }

    public GCMMessage(String[] receivers) {
        this.receivers = receivers;
        this.content_available=1;
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

    public void setContent_available(int content_available) {
        this.content_available = content_available;
    }

    public int getContent_available() {
        return content_available;
    }
    
}
