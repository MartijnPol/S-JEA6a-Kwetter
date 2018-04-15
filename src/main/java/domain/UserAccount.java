package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import utils.EncryptionHelper;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martijn van der Pol on 28-02-18
 **/

@Entity
@NamedQueries({
        @NamedQuery(name = "UserAccount.findByUsername", query = "SELECT account FROM UserAccount account WHERE account.username= :username"),
        @NamedQuery(name = "UserAccount.findByCredentials", query = "SELECT account FROM UserAccount account WHERE account.username= :username AND account.password = :password")
})
public class UserAccount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @NotNull
    @JsonIgnore
    private String password;

    @Column(unique = true)
    @Email
    private String mailAddress;

    @OneToOne(cascade = CascadeType.ALL)
    private UserProfile userProfile;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "accounts")
    private List<UserGroup> userGroups;

    /**
     * Empty constructor
     */
    public UserAccount() {
        this.userGroups = new ArrayList<UserGroup>();
    }

    /**
     * Constructor for the UserAccount class
     *
     * @param username    of the account
     * @param password    of the account
     * @param mailAddress of the account
     */
    public UserAccount(String username, String password, String mailAddress) {
        this();
        this.username = username;
        this.password = EncryptionHelper.encryptPassword(password);
        this.mailAddress = mailAddress;
        this.userProfile = new UserProfile(this, null, null, null, null);
    }

    //<editor-fold desc="Getters and Setters">

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = EncryptionHelper.encryptPassword(password);
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    //</editor-fold>

    public void addUserGroup(UserGroup userGroup) {
        this.userGroups.add(userGroup);
    }

}
