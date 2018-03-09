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
    @Column(columnDefinition = "int default 140")
    private int maxAmountOfCharacters;

    @NotNull
    private String message;

    @NotNull
    private Date timeOfPosting;

    @ManyToOne
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
        this.sender = sender;
        this.message = message;
        this.mentions = mentions;
        this.hashtags = hashtags;
        this.timeOfPosting = new Date();
    }

    //<editor-fold desc="Getters and Setters">

    public Long getId() {
        return id;
    }

    public int getMaxAmountOfCharacters() {
        return maxAmountOfCharacters;
    }

    public void setMaxAmountOfCharacters(int maxAmountOfCharacters) {
        this.maxAmountOfCharacters = maxAmountOfCharacters;
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

    public List<UserProfile> getMentions() {
        return mentions;
    }

    public List<Heart> getLikes() {
        return likes;
    }

    public List<Hashtag> getHashtags() {
        return hashtags;
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
