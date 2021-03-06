package service;

import dao.interfaces.KweetDao;
import domain.JPA;
import domain.Kweet;
import domain.UserProfile;
import event.KweetEvent;
import interceptor.LoggingInterceptor;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

import static utils.StaticHelperFunctions.isNull;

/**
 * Created by Martijn van der Pol on 09-03-18
 **/
@Interceptors(LoggingInterceptor.class)
@Stateless
public class KweetService {

    @Inject
    @JPA
    private KweetDao kweetDao;

    @Inject
    private Event<KweetEvent> kweetEvent;

    /**
     * Empty constructor
     */
    public KweetService() {

    }

    /**
     * Function to set the KweetDao via constructor injection
     *
     * @param kweetDao kweetDao
     */
    public KweetService(KweetDao kweetDao) {
        this.kweetDao = kweetDao;
    }

    /**
     * Function to save a Kweet to the database
     *
     * @param kweet the Kweet that needs to be saved
     */
    public void save(Kweet kweet) {
        if (!isNull(kweet)) {
            this.kweetDao.save(kweet);
        }
    }

    /**
     * Function to delete a Kweet stored in the database
     *
     * @param id of the Kweet that needs to be deleted
     */
    public void deleteById(Long id) {
        if (!isNull(id)) {
            this.kweetDao.deleteById(id);
        }
    }

    /**
     * Removes a Kweet from the DataBase.
     *
     * @param kweet kweet to remove
     */
    public void delete(Kweet kweet) {
        if (!isNull(kweet)) {
            this.kweetDao.delete(kweet);
        }
    }

    /**
     * Function to update a Kweet in the database
     *
     * @param kweet is the Kweet that needs to be updated in the database
     * @return the updated Kweet
     */
    public Kweet update(Kweet kweet) {
        if (!isNull(kweet)) {
            return this.kweetDao.update(kweet);
        }
        return null;
    }

    /**
     * Function to find a specific Kweet by it's id
     *
     * @param id is the id from the specific Kweet that needs to be retrieved
     * @return the Kweet object that matches with the given id
     */
    public Kweet findById(Long id) {
        if (!isNull(id)) {
            return this.kweetDao.findById(id);
        }
        return null;
    }

    /**
     * Function to find all kweets containing a given message
     *
     * @param message the message the Kweets has to contain
     * @return a collection of Kweets that match with the given message
     */
    public List<Kweet> findAllKweetsByMessage(String message) {
        if (!isNull(message)) {
            return kweetDao.findAllKweetsByMessage(message);
        }
        return null;
    }

    /**
     * Function to find all kweets by a given user
     *
     * @param senderId the id of the sender
     * @return a collection of Kweets that belong to the given user
     */
    public List<Kweet> findAllKweetsBySender(Long senderId) {
        return kweetDao.findAllKweetsBySender(senderId);
    }

    /**
     * Function to get all Kweets in the database
     *
     * @return all Kweets stored in the database
     */
    public List<Kweet> getAll() {
        return kweetDao.getAll();
    }

    /**
     * Function to convert a given list of UserProfiles to a List of converted JsonObjects
     *
     * @param kweets
     * @return a List of converted JsonObjects
     */
    public List<JsonObject> convertAllToJson(List<Kweet> kweets) {
        List<JsonObject> jsonObjects = new ArrayList<JsonObject>();

        for (Kweet kweet : kweets) {
            jsonObjects.add(kweet.toJson());
        }

        return jsonObjects;
    }

    /**
     * Functon to find all kweets by a given hashtag subject
     *
     * @param subject hashtag subject
     * @return a list of Kweets that are using the same hashtag
     */
    public List<Kweet> findAllKweetsByHashtagSubject(String subject) {
        return kweetDao.findAllKweetsByHashtagSubject(subject);
    }

    /**
     * Function to find all Kweets that a given UserProfile should have in it's timeline
     *
     * @param profile the profile
     * @return all Kweets from the followers
     */
    public List<Kweet> findAllKweetsFromFollowing(UserProfile profile) {
        return this.kweetDao.findAllKweetsFromFollowing(profile);
    }
}