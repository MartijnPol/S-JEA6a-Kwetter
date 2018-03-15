package service;

import domain.Kweet;
import domain.UserAccount;
import domain.UserProfile;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.Date;

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

        UserAccount MartijnPolAccount = new UserAccount("MartijnPol", "1234", "martijn.pol@hotmail.com");
        UserAccount HansDeGansAccount = new UserAccount("HansDeGans", "1234", "hans.degans@hotmail.com");

        UserProfile MartijnPolProfile = new UserProfile(MartijnPolAccount, "Martijn", "van der Pol", new Date());
        UserProfile HansDeGansProfile = new UserProfile(HansDeGansAccount, "Hans", "de Gans", new Date());

        MartijnPolProfile.addKweet(new Kweet(MartijnPolProfile, "Test"));

        MartijnPolAccount.setUserProfile(MartijnPolProfile);
        HansDeGansAccount.setUserProfile(HansDeGansProfile);

        userAccountService.save(MartijnPolAccount);
        userAccountService.save(HansDeGansAccount);
    }
}
