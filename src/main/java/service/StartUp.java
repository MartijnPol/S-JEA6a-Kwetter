package service;

import domain.Kweet;
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
        UserAccount MartijnPol = new UserAccount("MartijnPol", "1234", "martijn.pol@hotmail.com");
        UserAccount HansDeGans = new UserAccount("HansDeGans", "1234", "hans.degans@hotmail.com");

        MartijnPol.getUserProfile().addKweet(new Kweet(MartijnPol.getUserProfile(), "Test"));

        userAccountService.save(MartijnPol);
        userAccountService.save(HansDeGans);
    }
}
