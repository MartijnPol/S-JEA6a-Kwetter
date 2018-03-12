package domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Martijn van der Pol on 28-02-18
 **/

@Entity
@NamedQuery(name = "Kweet.findAllKweetsBySenderId", query = "SELECT kweet FROM Kweet kweet WHERE kweet.sender.id = :id")
public class Kweet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String message;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date timeOfPosting;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "SENDER_ID", nullable = false)
    private UserProfile sender;

    @OneToMany
    private List<UserProfile> mentions;

    @OneToMany
    private List<Heart> likes;

    @OneToMany
    private List<Hashtag> hashtags;

    /**
     * Empty constructor for the ORM
     */
    public Kweet() {
        this.mentions = new ArrayList<UserProfile>();
        this.likes = new ArrayList<Heart>();
        this.hashtags = new ArrayList<Hashtag>();
    }

    /**
     * Constructor for the Kweet class
     *
     * @param message  contains the content of this Kweet
     * @param mentions are all the people (UserProfiles) the person mentioned in the Kweet
     * @param hashtags are all the hashtags the person used in the Kweet
     */
    public Kweet(UserProfile sender, String message, List<UserProfile> mentions, List<Hashtag> hashtags) {
        if (message.length() > 140) {
            throw new IllegalArgumentException("Message needs to be within 140 characters.");
        } else {
            this.sender = sender;
            this.message = message;
            this.mentions = mentions;
            this.hashtags = hashtags;
            this.timeOfPosting = new Date();
        }
    }

    //<editor-fold desc="Getters and Setters">

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

    public Date getTimeOfPosting() {
        return timeOfPosting;
    }

    public void setTimeOfPosting(Date timeOfPosting) {
        this.timeOfPosting = timeOfPosting;
    }

    public UserProfile getSender() {
        return sender;
    }

    public void setSender(UserProfile sender) {
        this.sender = sender;
    }

    public List<UserProfile> getMentions() {
        return mentions;
    }

    public void setMentions(List<UserProfile> mentions) {
        this.mentions = mentions;
    }

    public List<Heart> getLikes() {
        return likes;
    }

    public void setLikes(List<Heart> likes) {
        this.likes = likes;
    }

    public List<Hashtag> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }


    //</editor-fold>

    //<editor-fold desc="Methods">
    /**
     * Method to add a like to the Kweet
     *
     * @param like is the Like itself
     */
    public void addLike(Heart like) {
        this.likes.add(like);
    }
    //</editor-fold>

}
