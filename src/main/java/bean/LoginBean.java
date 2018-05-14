package bean;

import domain.UserAccount;
import service.UserAccountService;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

import static org.omnifaces.util.Faces.redirect;

/**
 * Created by Martijn van der Pol on 25-03-18
 **/
@Named("LoginBean")
@ViewScoped
public class LoginBean implements Serializable {

    @Inject
    private UserAccountService userAccountService;

    @Inject
    private SessionBean sessionBean;

    private String username;
    private String password;

    /**
     * Function to try to authenticate a UserAccount by it's given username and password
     */
    public void Login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            request.login(this.username, this.password);
        } catch (ServletException e) {
            e.printStackTrace();
            return;
        }

        UserAccount loggedInUser = this.userAccountService.findByUsername(request.getUserPrincipal().getName());
        this.sessionBean.setLoggedInUser(loggedInUser);

        boolean isRegular = request.isUserInRole("RegularRole");
        boolean isAdmin = request.isUserInRole("AdminRole");

        redirect("pages/regular/dashboard.xhtml");
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
}
