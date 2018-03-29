package service;

import domain.*;

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

    @Inject
    private UserGroupService userGroupService;

    public StartUp() {

    }

    @PostConstruct
    public void initData() {

        UserAccount MartijnPolAccount = new UserAccount("MartijnPol", "1234", "martijn.pol@hotmail.com");
        UserAccount HansDeGansAccount = new UserAccount("HansDeGans", "1234", "hans.degans@hotmail.com");

        UserProfile MartijnPolProfile = new UserProfile(MartijnPolAccount, "Martijn", "van der Pol", new Date());
        UserProfile HansDeGansProfile = new UserProfile(HansDeGansAccount, "Hans", "de Gans", new Date());

        Hashtag hashtag = new Hashtag("#LoveJEA");

        Kweet martijnKweet = new Kweet(MartijnPolProfile, "Java is life");
        martijnKweet.addHashtag(hashtag);

        Kweet hansKweet = new Kweet(HansDeGansProfile, "JavaEE is pretty cool stuff to learn about!");
        hansKweet.addHashtag(hashtag);

        MartijnPolProfile.addKweet(martijnKweet);
        HansDeGansProfile.addKweet(hansKweet);

        MartijnPolAccount.setUserProfile(MartijnPolProfile);
        HansDeGansAccount.setUserProfile(HansDeGansProfile);

        UserGroup regularUserGroup = new UserGroup("Regular");
        regularUserGroup.addUser(MartijnPolAccount);
        regularUserGroup.addUser(HansDeGansAccount);

        userAccountService.save(MartijnPolAccount);
        userAccountService.save(HansDeGansAccount);

        userGroupService.save(regularUserGroup);
    }
}
