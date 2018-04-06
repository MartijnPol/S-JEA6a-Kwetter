package bean;

import domain.UserAccount;
import org.omnifaces.util.Faces;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.ServletException;
import java.io.Serializable;

import static org.omnifaces.util.Faces.invalidateSession;
import static org.omnifaces.util.Faces.redirect;

/**
 * Created by Martijn van der Pol on 25-03-18
 **/
@Named("SessionBean")
@SessionScoped
public class SessionBean implements Serializable {

    private UserAccount loggedInUser;

    public void Logout() throws ServletException {
        loggedInUser = null;

        Faces.logout();
        invalidateSession();
        redirect("login.xhtml?faces-redirect=true");
    }

    public UserAccount getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(UserAccount loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

}