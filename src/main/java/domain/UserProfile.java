package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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

    private byte[] avatar;

    private String firstName;

    private String lastName;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Size(max = 160, message = "Biography can't be longer then 160 characters.")
    private String biography;

    @NotNull
    @OneToOne(optional = false)
    @JsonIgnore
    private UserAccount userAccount;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sender")
    private List<Kweet> kweets;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Kweet> mentionKweets;

    @JsonIgnore
    @ManyToMany
    private List<UserProfile> followers;

    @JsonIgnore
    @Transient
    private List<UserProfile> followees;

    /**
     * Empty constructor for the ORM
     */
    public UserProfile() {
        this.followers = new ArrayList<UserProfile>();
        this.followees = new ArrayList<UserProfile>();
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
    public UserProfile(UserAccount userAccount, String firstName, String lastName, Date dateOfBirth) {
        this();
        this.userAccount = userAccount;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("id", this.id)
                .add("firstName", this.firstName)
                .add("lastName", this.lastName)
                .add("userAccountId", this.userAccount.getId())
                .add("kweets", this.kweets.size())
                .add("followers", this.followers.size())
                .add("followees", this.followees.size())
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

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
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

    public List<UserProfile> getFollowees() {
        return followees;
    }

    public void setFollowees(List<UserProfile> followees) {
        this.followees = followees;
    }


    //</editor-fold>

    public void addKweet(Kweet kweet) {
        this.kweets.add(kweet);
    }

    public void addMention(Kweet mentionKweet) {
        this.mentionKweets.add(mentionKweet);
    }

}
