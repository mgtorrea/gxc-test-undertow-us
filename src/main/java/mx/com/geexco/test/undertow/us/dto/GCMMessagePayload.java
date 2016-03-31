package mx.com.geexco.test.undertow.us.dto;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author J12411
 */
public class GCMMessagePayload {

    @SerializedName(value = "message")
    private String message;

    public GCMMessagePayload(String message) {
        this.message = message;
    }

    public GCMMessagePayload() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
