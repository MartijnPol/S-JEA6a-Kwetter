package bean;

import service.UserAccountService;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Martijn van der Pol on 24-03-18
 **/

@Named
@Dependent
public class AuthenticationBean {

    @Inject
    private UserAccountService userAccountService;

    public void Login() {

    }

    public void Logout() {

    }

}
