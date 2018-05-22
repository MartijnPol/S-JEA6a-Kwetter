package domain;

import rest.KweetResource;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.UriBuilder;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static utils.HrefBuilder.build;

/**
 * Created by Martijn van der Pol on 28-02-18
 **/
@Entity
@NamedQueries({
        @NamedQuery(name = "UserProfile.findByUsername", query = "SELECT profile FROM UserProfile profile WHERE profile.userAccount.username= :username")
})
public class UserProfile implements Serializable, RestObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String avatarUrl;

    private String firstName;

    private String lastName;

    private String location;

    //    @Temporal(TemporalType.DATE)
    private String dateOfBirth;

    @Size(max = 160, message = "Biography can't be longer then 160 characters.")
    private String biography;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private UserAccount userAccount;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sender", orphanRemoval = true)
    private List<Kweet> kweets;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    private List<Kweet> mentionKweets;

    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "following")
    private List<UserProfile> followers;

    @ManyToMany
    private List<UserProfile> following;

    /**
     * Empty constructor for the ORM
     */
    public UserProfile() {
        this.followers = new ArrayList<UserProfile>();
        this.following = new ArrayList<UserProfile>();
        this.kweets = new ArrayList<Kweet>();
        this.mentionKweets = new ArrayList<Kweet>();
    }

    /**
     * Constructor for the UserProfile class
     *
     * @param firstName   is the first name of the person this account belongs to
     * @param lastName    is the last name of the person this account belongs to
     * @param dateOfBirth is the birthday of the person this account belongs to
     */
    public UserProfile(UserAccount userAccount, String firstName, String lastName, String dateOfBirth, String location) {
        this();
        this.userAccount = userAccount;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.location = location;
    }

    public JsonObject toJson() {

        // GetKweetsByUserProfile Link (HATEOS)
        UriBuilder kweetsUriBuilder = UriBuilder.fromResource(KweetResource.class).path(KweetResource.class, "findAllKweetsByUser");
        Link allKweetsLink = Link.fromUri(kweetsUriBuilder.build(this.id)).rel("self").build();

        return Json.createObjectBuilder()
                .add("id", this.id)
                .add("username", this.userAccount.getUsername())
                .add("email", this.userAccount.getMailAddress())
                .add("location", this.location)
                .add("firstName", this.firstName)
                .add("lastName", this.lastName)
                .add("biography", this.biography)
                .add("dateOfBirth", this.dateOfBirth)
                .add("avatarUrl", this.avatarUrl)
                .add("amountFollowers", this.followers.size())
                .add("amountFollowing", this.following.size())
                .add("kweets", allKweetsLink.getUri().getPath())
                .build();
    }

    public JsonObject toHref() {
        return Json.createObjectBuilder()
                .add("href", build(this))
                .build();
    }

    //<editor-fold desc="Getters and Setters">

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public List<Kweet> getKweets() {
        return kweets;
    }

    public void setKweets(List<Kweet> kweets) {
        this.kweets = kweets;
    }

    public List<Kweet> getMentionKweets() {
        return mentionKweets;
    }

    public void setMentionKweets(List<Kweet> mentionKweets) {
        this.mentionKweets = mentionKweets;
    }

    public List<UserProfile> getFollowers() {
        return followers;
    }

    public void setFollowers(List<UserProfile> followers) {
        this.followers = followers;
    }

    public List<UserProfile> getFollowing() {
        return following;
    }

    public void setFollowing(List<UserProfile> followees) {
        this.following = followees;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    //</editor-fold>

    public void addKweet(Kweet kweet) {
        this.kweets.add(kweet);
    }

    public void addMention(Kweet mentionKweet) {
        this.mentionKweets.add(mentionKweet);
    }

    public void addFollower(UserProfile follower) {
        this.followers.add(follower);
    }

    public void addFollowee(UserProfile followee) {
        this.following.add(followee);
    }

}
