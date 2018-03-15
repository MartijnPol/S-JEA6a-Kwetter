package service;

import dao.interfaces.KweetDao;
import domain.JPA;
import domain.Kweet;
import event.KweetEvent;
import interceptor.LoggingInterceptor;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

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
        kweetEvent.fire(new KweetEvent(kweet));
    }

    /**
     * Function to delete a Kweet stored in the database
     * @param id of the Kweet that needs to be deleted
     */
    public void deleteById(Long id) {
        this.kweetDao.deleteById(id);
    }

    /**
     * Function to update a Kweet in the database
     *
     * @param kweet is the Kweet that needs to be updated in the database
     * @return the updated Kweet
     */
    public Kweet update(Kweet kweet) {
        return this.kweetDao.update(kweet);
    }

    /**
     * Function to find a specific Kweet by it's id
     * @param id is the id from the specific Kweet that needs to be retrieved
     * @return the Kweet object that matches with the given id
     */
    public Kweet findById(Long id) {
        return this.kweetDao.findById(id);
    }

    /**
     * Function to find all kweets containing a given message
     * @param message the message the Kweets has to contain
     * @return a collection of Kweets that match with the given message
     */
    public List<Kweet> findAllKweetsByMessage(String message) {
        return kweetDao.findAllKweetsByMessage(message);
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

}