package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Martijn van der Pol on 28-02-18
 **/

@Entity
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

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    /**
     * Empty constructor for the ORM
     */
    public UserAccount() {
    }

    /**
     * Constructor for the UserAccount class
     *
     * @param username    of the account
     * @param password    of the account
     * @param mailAddress of the account
     */
    public UserAccount(String username, String password, String mailAddress) {
        this.username = username;
        this.password = password;
        this.mailAddress = mailAddress;
        this.userProfile = new UserProfile();
        this.role = UserRole.REGULAR;
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
        this.password = password;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    //</editor-fold>

}
