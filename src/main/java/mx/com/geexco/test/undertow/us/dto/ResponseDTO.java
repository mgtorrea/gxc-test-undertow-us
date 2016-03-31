package mx.com.geexco.test.undertow.us.dto;

/**
 *
 * @author J12411
 */
public class ResponseDTO {

    private int code;
    private String text;

    public ResponseDTO(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public ResponseDTO(int code) {
        this.code = code;
        this.text = null;
    }

    public ResponseDTO() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
