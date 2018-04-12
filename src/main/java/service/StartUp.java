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
        UserAccount AdminAccount = new UserAccount("admin", "admin", "admin@hotmail.com");

        UserProfile MartijnPolProfile = new UserProfile(MartijnPolAccount, "Martijn", "van der Pol", new Date());
        UserProfile AdminProfile = new UserProfile(AdminAccount, "Ad", "Min", new Date());

        Hashtag hashtag = new Hashtag("#LoveJEA");

        Kweet martijnKweet = new Kweet(MartijnPolProfile, "Java is life");
//        martijnKweet.addHashtag(hashtag);

        Kweet hansKweet = new Kweet(AdminProfile, "JavaEE is pretty cool stuff to learn about!");
        hansKweet.addHashtag(hashtag);

        MartijnPolProfile.addKweet(martijnKweet);
        AdminProfile.addKweet(hansKweet);

        MartijnPolAccount.setUserProfile(MartijnPolProfile);
        AdminAccount.setUserProfile(AdminProfile);

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
