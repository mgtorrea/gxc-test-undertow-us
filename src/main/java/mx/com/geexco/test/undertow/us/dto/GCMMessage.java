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
    @SerializedName(value = "notification")
    private Map<String, String> notificationData = new HashMap<>();
    @SerializedName(value = "data")
    private Map<String, String> payload = new HashMap<>();
    @SerializedName(value = "content_available")
    private boolean content_available;

    public GCMMessage() {
        this.content_available=true;
    }

    public void addData(String key, String value) {
        payload.put(key, value);
    }
    
    public void addNotificationData(String key, String value) {
        notificationData.put(key, value);
    }

    public GCMMessage(String[] receivers) {
        this.receivers = receivers;
        this.content_available=true;
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
    
    public Map<String, String> getNotificationData() {
        return notificationData;
    }

    public void setNotificationData(Map<String, String> notificationData) {
        this.notificationData = notificationData;
    }

    public void setContent_available(boolean content_available) {
        this.content_available = content_available;
    }

    public boolean getContent_available() {
        return content_available;
    }
    
}
