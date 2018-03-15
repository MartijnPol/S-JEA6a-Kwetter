package domain;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Martijn van der Pol on 28-02-18
 **/

@Entity
@NamedQueries({
        @NamedQuery(name = "Kweet.findAllKweetsByMessage", query = "SELECT kweet FROM Kweet kweet WHERE kweet.message LIKE :message")
})
public class Kweet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 140, message = "Message can't be longer then 140 characters.")
    private String message;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date timeOfPosting;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "SENDER_ID", nullable = false)
    private UserProfile sender;

    @OneToMany
    private List<UserProfile> mentions;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Heart> likes;

    @OneToMany(cascade = CascadeType.ALL)
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
     */
    public Kweet(UserProfile sender, String message) {
        this();
        if (message.length() > 140) {
            throw new IllegalArgumentException("Message needs to be within 140 characters.");
        } else {
            this.sender = sender;
            this.message = message;
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
     * Method to add a like to a Kweet
     *
     * @param like is the Like itself
     */
    public void addLike(Heart like) {
        this.likes.add(like);
    }

    /**
     * Function to add a hashtag to a Kweet
     *
     * @param hashtag
     */
    public void addHashtag(Hashtag hashtag) {
        hashtag.addKweet(this);
        this.hashtags.add(hashtag);
    }

    /**
     * Function to add a mention to a Kweet
     *
     * @param mention
     */
    public void addMention(UserProfile mention) {
        mention.addMention(this);
        this.mentions.add(mention);
    }
    //</editor-fold>

    public JsonObject toJson() {

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        return Json.createObjectBuilder()
                .add("id", this.id)
                .add("message", this.message)
                .add("timeOfPosting", dateFormat.format(this.timeOfPosting))
                .add("senderId", this.sender.getId())
                .build();
    }

}
