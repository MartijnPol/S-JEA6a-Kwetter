package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Martijn van der Pol on 28-02-18
 **/

@Entity
public class Like {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @OneToOne
    private UserProfile sender;

    private Date timeOfLiking;

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
    public Like(UserProfile sender) {
        this.timeOfLiking = new Date();
        this.sender = sender;
    }

    //<editor-fold desc="Getters and Setters">
    public Date getTimeOfLiking() {
        return timeOfLiking;
    }

    public UserProfile getSender() {
        return sender;
    }

    //</editor-fold>

}
