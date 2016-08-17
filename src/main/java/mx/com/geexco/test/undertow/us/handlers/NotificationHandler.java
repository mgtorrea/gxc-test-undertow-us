package mx.com.geexco.test.undertow.us.handlers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.Arrays;
import mx.com.geexco.test.undertow.us.dao.RegistrationSource;
import mx.com.geexco.test.undertow.us.dto.GCMMessage;
import mx.com.geexco.test.undertow.us.dto.NotificationDTO;
import mx.com.geexco.test.undertow.us.dto.ResponseDTO;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author gxc-mg
 */
public class NotificationHandler extends AbstractJSONProcessorHandler {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final GsonBuilder gb = new GsonBuilder();
    private final HttpClientBuilder cb;

    RegistrationSource reg;

    public NotificationHandler(RegistrationSource reg, String apiKey) {
        this.reg = reg;
        Header header = new BasicHeader(HttpHeaders.AUTHORIZATION, "key=" + apiKey);
        cb = HttpClientBuilder.create().setDefaultHeaders(Arrays.asList(header));
    }

    private int notifica(String registrationId, String title, String msgContent, String info) {
        CloseableHttpClient client = cb.build();
        HttpPost post = new HttpPost("https://fcm.googleapis.com/fcm/send");
        GCMMessage msg = new GCMMessage(new String[]{registrationId});
        /*msg.addNotificationData("title", title);
        msg.addNotificationData("body", msgContent);
        msg.addNotificationData("sound", "default");*/
        msg.addData("content-available", "1");
        msg.addData("info", info);
        String data = gb.create().toJson(msg);
        log.info("Mensaje enviado:"+data);
        post.setEntity(new StringEntity(data, ContentType.APPLICATION_JSON));
        try {
            CloseableHttpResponse response = client.execute(post);
        } catch (IOException ex) {
            log.error("Error sending notification: ", ex);
            return -1;
        }
        return 0;
    }

    @Override
    public String handleJSONMessage(String msg) {
        Gson gson = gb.create();
        if (msg.isEmpty()) {
            return gson.toJson(new ResponseDTO(-1));
        }
        NotificationDTO dto;
        try {
            dto = gson.fromJson(msg, NotificationDTO.class);
        } catch (Exception e) {
            log.info("Error getting DTO from '" + msg + "'");
            return gson.toJson(new ResponseDTO(-2));
        }
        String id = reg.getDevice(dto.getId());
        if (id == null) {
            return gson.toJson(new ResponseDTO(-3));
        }
        int res = notifica(id, dto.getTitle(), dto.getMessage(), dto.getInfo());
        if (res != 0) {
            return gson.toJson(new ResponseDTO(-4));
        }
        return gson.toJson(new ResponseDTO(0));
    }

}
