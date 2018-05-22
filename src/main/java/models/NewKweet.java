package models;

/**
 * Created by Martijn van der Pol on 22-05-18
 **/
public class NewKweet {

    private Long id;
    private String message;

    public NewKweet() {

    }

    public NewKweet(Long id, String message) {
        this.id = id;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
