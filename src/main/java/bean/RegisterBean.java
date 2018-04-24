package bean;

import domain.UserAccount;
import domain.UserGroup;
import service.UserAccountService;
import service.UserGroupService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

import static org.omnifaces.util.Faces.redirect;

/**
 * Created by Martijn van der Pol on 25-03-18
 **/
@Named("RegisterBean")
@ViewScoped
public class RegisterBean implements Serializable {

    @Inject
    private UserAccountService userAccountService;

    @Inject
    private UserGroupService userGroupService;

    private UserGroup regularUserGroup;

    private String username;
    private String email;
    private String password;

    @PostConstruct
    public void init() {
        this.regularUserGroup = this.userGroupService.findByName("Regular");
    }

    /**
     * Function to try to authenticate a UserAccount by it's given username and password
     */
    public void Register() {
        UserAccount newAccount = new UserAccount(this.username, this.password, this.email);
        this.userAccountService.save(newAccount);
        this.regularUserGroup.addUser(newAccount);
        this.userGroupService.update(this.regularUserGroup);
        redirect("login.xhtml?faces-redirect=true");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
