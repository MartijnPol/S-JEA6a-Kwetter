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

        // Hashtags
        Hashtag JEAHashtag = new Hashtag("JEA");
        Hashtag SOLIDHashtag = new Hashtag("SOLID");

        // Martijn van der Pol
        UserAccount MartijnPolAccount = new UserAccount("MartijnPol", "1234", "martijn.pol@hotmail.com");
        UserProfile MartijnPolProfile = new UserProfile(MartijnPolAccount, "Martijn", "van der Pol", new Date(), "Utrecht, The Netherlands");
        MartijnPolProfile.setBiography("FairChain expert to make asparagus fair again.");
        MartijnPolProfile.setAvatarUrl("https://avatars3.githubusercontent.com/u/25583331?s=400&u=6ad434b6e128ea5e198c00308ca1470a8610a007&v=4");

        Kweet martijnKweet = new Kweet(MartijnPolProfile, "Do you even C#?");

        // John Doe
        UserAccount JohnAccount = new UserAccount("JohnDoe", "admin", "john@doe.com");
        UserProfile JohnProfile = new UserProfile(JohnAccount, "John", "Doe", new Date(), "New York City, United States of America");
        JohnProfile.setBiography("Senior Java SOLID REST C# Architecture Network Endpoint Architect @ Ordina");
        JohnProfile.setAvatarUrl("https://s3.eu-central-1.amazonaws.com/artistarea.gallereplay.com/production/user_9/picture_2405201614728.jpg");

        Kweet johnFirstKweet = new Kweet(JohnProfile, "Bruh, do you even C#?");
        Kweet johnSecondKweet = new Kweet(JohnProfile, "Solid Java code is a fairy tale. It doesn't exist.");

        // Lisa Hampton
        UserAccount LisaAccount = new UserAccount("LisaHampton", "1234", "lisa@hampton.com");
        UserProfile LisaProfile = new UserProfile(LisaAccount, "Lisa", "Hampton", new Date(), "Amsterdam, the Netherlands");
        LisaProfile.setBiography("Make Java, not war.");
        LisaProfile.setAvatarUrl("https://orig00.deviantart.net/9af2/f/2011/227/4/8/profile_picture_by_coi_stock-d46ovqt.jpg");

        Kweet lisaFirstKweet = new Kweet(LisaProfile, "I thought Python was a snake?");
        Kweet lisaSecondKweet = new Kweet(LisaProfile, "Wow, just had 3204 exceptions when I tried to make JPA work!");

        // Add Hashtags to Kweets
        johnFirstKweet.addHashtag(JEAHashtag);
        lisaSecondKweet.addHashtag(JEAHashtag);

        johnSecondKweet.addHashtag(JEAHashtag);
        johnSecondKweet.addHashtag(SOLIDHashtag);

        martijnKweet.addHashtag(SOLIDHashtag);

        // Add Kweets to UserProfile's
        MartijnPolProfile.addKweet(martijnKweet);

        JohnProfile.addKweet(johnFirstKweet);
        JohnProfile.addKweet(johnSecondKweet);

        LisaProfile.addKweet(lisaFirstKweet);
        LisaProfile.addKweet(lisaSecondKweet);

        // Add Followers and Followees
        MartijnPolProfile.addFollowee(JohnProfile);
        MartijnPolProfile.addFollowee(LisaProfile);
        LisaProfile.addFollower(MartijnPolProfile);
        JohnProfile.addFollower(MartijnPolProfile);

        JohnProfile.addFollowee(MartijnPolProfile);
        MartijnPolProfile.addFollower(JohnProfile);

        LisaProfile.addFollowee(JohnProfile);
        JohnProfile.addFollower(LisaProfile);

        // Add profiles and roles to accounts
        MartijnPolAccount.setUserProfile(MartijnPolProfile);
        JohnAccount.setUserProfile(JohnProfile);
        LisaAccount.setUserProfile(LisaProfile);

        UserGroup regularUserGroup = new UserGroup("Regular");
        regularUserGroup.addUser(MartijnPolAccount);
        regularUserGroup.addUser(LisaAccount);

        UserGroup adminUserGroup = new UserGroup("Admin");
        adminUserGroup.addUser(JohnAccount);

        // Save accounts
        userAccountService.save(MartijnPolAccount);
        userAccountService.save(JohnAccount);
        userAccountService.save(LisaAccount);

        userGroupService.save(regularUserGroup);
        userGroupService.save(adminUserGroup);
    }
}
