package domain;

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
}
