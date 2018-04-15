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
        UserAccount AdminAccount = new UserAccount("JohnDoe", "admin", "admin@hotmail.com");

        UserProfile MartijnPolProfile = new UserProfile(MartijnPolAccount, "Martijn", "van der Pol", new Date(), "Utrecht, The Netherlands");
        MartijnPolProfile.setBiography("FairChain expert to make asparagus fair again.");
        MartijnPolProfile.setAvatarUrl("https://avatars3.githubusercontent.com/u/25583331?s=400&u=6ad434b6e128ea5e198c00308ca1470a8610a007&v=4");

        UserProfile JohnProfile = new UserProfile(AdminAccount, "John", "Doe", new Date(), "New York City, United States of America");
        JohnProfile.setBiography("What's Kwetter without it's admins?");
        JohnProfile.setAvatarUrl("https://s3.eu-central-1.amazonaws.com/artistarea.gallereplay.com/production/user_9/picture_2405201614728.jpg");

        Kweet martijnKweet = new Kweet(MartijnPolProfile, "Java is life");

        Kweet johnFirstKweet = new Kweet(JohnProfile, "JavaEE is pretty cool stuff to learn about!");
        Hashtag hashtag = new Hashtag("LoveJEA");
        johnFirstKweet.addHashtag(hashtag);
        martijnKweet.addHashtag(hashtag);

        Kweet johnSecondKweet = new Kweet(JohnProfile, "Solid Java code is a fairy tale. It doesn't exist.");

        MartijnPolProfile.addKweet(martijnKweet);
        JohnProfile.addKweet(johnFirstKweet);
        JohnProfile.addKweet(johnSecondKweet);

        MartijnPolProfile.addFollowee(JohnProfile);
        JohnProfile.addFollowee(MartijnPolProfile);

        MartijnPolAccount.setUserProfile(MartijnPolProfile);
        AdminAccount.setUserProfile(JohnProfile);

        UserGroup regularUserGroup = new UserGroup("Regular");
        regularUserGroup.addUser(MartijnPolAccount);

        UserGroup adminUserGroup = new UserGroup("Admin");
        adminUserGroup.addUser(AdminAccount);

        userAccountService.save(MartijnPolAccount);
        userAccountService.save(AdminAccount);

        userGroupService.save(regularUserGroup);
        userGroupService.save(adminUserGroup);
    }
}
