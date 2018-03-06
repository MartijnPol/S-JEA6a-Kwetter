package service;

import domain.JPA;
import domain.UserAccount;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 * Created by Martijn van der Pol on 02-03-18
 **/
@Singleton
@Startup
public class StartUp {

    @Inject
    private UserAccountService userAccountService;

    public StartUp() {

    }

    @PostConstruct
    public void initData() {
        UserAccount newAccount = new UserAccount("MartijnPol", "1234", "martijn.pol@hotmail.com");
        userAccountService.addUserAccount(newAccount);
    }
    
}
