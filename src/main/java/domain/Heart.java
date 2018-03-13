package domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Martijn van der Pol on 28-02-18
 **/

@Entity
public class Heart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeOfLiking;

    @NotNull
    @OneToOne
    private UserProfile sender;

    @ManyToOne
    private Kweet parentKweet;

    /**
     * Empty constructor for the ORM
     */
    public Heart() {

    }

    /**
     * Constructor for the Like class
     *
     * @param sender is the UserProfile that gave the like
     */
    public Heart(UserProfile sender, Kweet parentKweet) {
        this.sender = sender;
        this.parentKweet = parentKweet;
        this.timeOfLiking = new Date();
    }

    //<editor-fold desc="Getters and Setters">

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTimeOfLiking() {
        return timeOfLiking;
    }

    public void setTimeOfLiking(Date timeOfLiking) {
        this.timeOfLiking = timeOfLiking;
    }

    public UserProfile getSender() {
        return sender;
    }

    public void setSender(UserProfile sender) {
        this.sender = sender;
    }

    public Kweet getParentKweet() {
        return parentKweet;
    }

    public void setParentKweet(Kweet parentKweet) {
        this.parentKweet = parentKweet;
    }

    //</editor-fold>

}
