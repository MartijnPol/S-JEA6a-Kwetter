package bean;

import domain.UserAccount;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by Martijn van der Pol on 25-03-18
 **/
@Named("SessionBean")
@SessionScoped
public class SessionBean implements Serializable {

    private UserAccount loggedInUser;

    public void InvalidateSession() {
        loggedInUser = null;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

    public UserAccount getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(UserAccount loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}