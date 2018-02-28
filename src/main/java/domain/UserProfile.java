package domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Martijn van der Pol on 28-02-18
 **/

@Entity
public class UserProfile {

    @Id
    @GeneratedValue
    private Long id;

    private byte[] avatar;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private Date dateOfBirth;

    private String biography;

    @OneToMany
    private List<Kweet> kweets;

    @ManyToMany
    private List<UserProfile> followers;

    @Transient
    private List<UserProfile> followees;

    /**
     * Empty constructor for the ORM
     */
    public UserProfile() {
        this.followers = new ArrayList<UserProfile>();
        this.followees = new ArrayList<UserProfile>();
        this.kweets = new ArrayList<Kweet>();
    }

    /**
     * Constructor for the UserProfile class
     *
     * @param firstName   is the first name of the person this account belongs to
     * @param lastName    is the last name of the person this account belongs to
     * @param dateOfBirth is the birthday of the person this account belongs to
     */
    public UserProfile(String firstName, String lastName, Date dateOfBirth) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    //<editor-fold desc="Getters and Setters">
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

}
