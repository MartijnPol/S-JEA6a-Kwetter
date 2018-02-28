package domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Martijn van der Pol on 28-02-18
 **/

@Entity
public class Like implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private Date timeOfLiking;

    @NotNull
    @OneToOne
    private UserProfile sender;

    @ManyToOne
    private Kweet parentKweet;

    /**
     * Empty constructor for the ORM
     */
    public Like() {

    }

    /**
     * Constructor for the Like class
     *
     * @param sender is the UserProfile that gave the like
     */
    public Like(UserProfile sender, Kweet parentKweet) {
        this.timeOfLiking = new Date();
        this.sender = sender;
        this.parentKweet = parentKweet;
    }

    //<editor-fold desc="Getters and Setters">
    public Date getTimeOfLiking() {
        return timeOfLiking;
    }

    public UserProfile getSender() {
        return sender;
    }

    public Kweet getParentKweet() {
        return parentKweet;
    }

    //</editor-fold>

}
