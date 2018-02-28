package domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Martijn van der Pol on 28-02-18
 **/

@Entity
public class UserAccount implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String username;

    @NotNull
    @Size(min = 5, max = 16)
    private String password;

    @Column(unique = true)
    @Email
    private String mailAddress;

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
    public UserAccount(String username, String password, String mailAddress, UserRole role) {
        this.username = username;
        this.password = password;
        this.mailAddress = mailAddress;
        this.role = role;
    }

    //<editor-fold desc="Getters and Setters">
    public Long getId() {
        return id;
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
    //</editor-fold>

}
