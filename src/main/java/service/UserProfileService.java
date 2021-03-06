package service;

import dao.interfaces.UserProfileDao;
import domain.JPA;
import domain.Kweet;
import domain.UserProfile;
import interceptor.LoggingInterceptor;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

import static utils.StaticHelperFunctions.isNull;

/**
 * Created by Martijn van der Pol on 02-03-18
 **/
@Interceptors(LoggingInterceptor.class)
@Stateless
public class UserProfileService {

    @Inject
    @JPA
    private UserProfileDao userProfileDao;

    /**
     * Empty constructor
     */
    public UserProfileService() {

    }

    /**
     * Function to set the UserProfileDao via constructor injection
     *
     * @param userProfileDao userProfileDao
     */
    public UserProfileService(UserProfileDao userProfileDao) {
        this.userProfileDao = userProfileDao;
    }

    /**
     * Function to update a given UserProfile object in the database
     *
     * @param userProfile the UserProfile object that needs to be updated in the database
     * @return the updated UserProfile object
     */
    public UserProfile update(UserProfile userProfile) {
        return this.userProfileDao.update(userProfile);
    }

    /**
     * Function to get all UserProfiles stored in the database
     *
     * @return A collection of all UserProfiles stored in the database
     */
    public List<UserProfile> getAll() {
        return userProfileDao.getAll();
    }

    /**
     * Function to find a specific UserProfile by it's id
     *
     * @param id is the id of the UserProfile that needs to be retrieved
     * @return the UserProfile found
     */
    public UserProfile findById(Long id) {
        if (!isNull(id)) {
            return this.userProfileDao.findById(id);
        }
        return null;
    }

    /**
     * Function to count all UserProfiles stored in the database
     *
     * @return the amount of UserProfiles stored in the database
     */
    public Long countAll() {
        return userProfileDao.countAll();
    }

    /**
     * Function to find a UserProfile by it's username
     *
     * @param username the username of the user
     * @return a UserProfile object that belongs to the given username
     */
    public UserProfile findByUsername(String username) {
        if (!isNull(username)) {
            return this.userProfileDao.findByUsername(username);
        }
        return null;
    }

    /**
     * Function to find all kweets from a specific UserProfile id
     *
     * @param id is the UserProfile id
     * @return a collection of Kweets from the given UserProfile
     */
    public List<Kweet> getAllKweetsById(Long id) {
        if (!isNull(id)) {
            return this.userProfileDao.findById(id).getKweets();
        }
        return null;
    }

    /**
     * Function to convert a given list of UserProfiles to a List of converted JsonObjects
     *
     * @param userProfiles are the UserProfiles that needed to be converted
     * @return a List of converted JsonObjects
     */
    public List<JsonObject> convertAllToJson(List<UserProfile> userProfiles) {
        List<JsonObject> jsonObjects = new ArrayList<JsonObject>();

        for (UserProfile userProfile : userProfiles) {
            jsonObjects.add(userProfile.toJson());
        }

        return jsonObjects;
    }
}

