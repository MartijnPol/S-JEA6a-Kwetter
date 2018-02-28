package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;

/**
 * Created by Martijn van der Pol on 28-02-18
 **/

@Entity
public class UserAccount implements Serializable {

    @Id @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String mailAddress;

    /**
     * Empty constructor for the ORM
     */
    public UserAccount() { }

    /**
     * Constructor
     * @param username of the account
     * @param password of the account
     * @param mailAddress of the account
     */
    public UserAccount(String username, String password, String mailAddress) {
        this.username = username;
        this.password = password;
        this.mailAddress = mailAddress;
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
    //</editor-fold>

}
