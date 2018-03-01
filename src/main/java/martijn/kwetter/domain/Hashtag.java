package martijn.kwetter.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by Martijn van der Pol on 28-02-18
 **/

@Entity
public class Hashtag implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String subject;

    @ManyToOne
    private Kweet parentKweet;

    /**
     * Empty constructor for the ORM
     */
    public Hashtag() {

    }

    /**
     * Constructor for the Hashtag class
     *
     * @param subject     is the subject of the hashtag, for example '#DonaldTrump'
     * @param parentKweet is the parent Kweet of the hashtag
     */
    public Hashtag(Kweet parentKweet, String subject) {
        this.parentKweet = parentKweet;
        this.subject = subject;
    }

    //<editor-fold desc="Getters and Setters">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Kweet getParentKweet() {
        return parentKweet;
    }

    public void setParentKweet(Kweet parentKweet) {
        this.parentKweet = parentKweet;
    }
    //</editor-fold>
}