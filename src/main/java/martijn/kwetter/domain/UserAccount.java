package martijn.kwetter.domain;

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
    private String passwordHash;

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
    public UserAccount(String username, String password, String mailAddress) {
        this.username = username;
        this.passwordHash = hashPassword(password);
        this.mailAddress = mailAddress;
        this.role = UserRole.REGULAR;
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

    public void setPassword(String password) {
        this.passwordHash = hashPassword(password);
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

    /**
     * Function to hash a given a password
     *
     * @param password given password
     * @return hash
     */
    private String hashPassword(String password) {
        return null;
    }

}
