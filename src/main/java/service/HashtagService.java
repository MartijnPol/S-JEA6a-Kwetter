package service;

import dao.interfaces.HashtagDao;
import domain.Hashtag;
import domain.JPA;
import interceptor.LoggingInterceptor;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martijn van der Pol on 12-03-18
 **/
@Interceptors(LoggingInterceptor.class)
@Stateless
public class HashtagService {

    @Inject
    @JPA
    private HashtagDao hashtagDao;

    /**
     * Empty constructor
     */
    public HashtagService() {

    }

    /**
     * Function to set the HashtagDao via constructor injection
     * @param hashtagDao hashtagDao
     */
    public HashtagService(HashtagDao hashtagDao) {
        this.hashtagDao = hashtagDao;
    }

    /**
     * Function to save a given Hashtag to the database
     *
     * @param hashtag the hashtag that needs to be saved
     * @return saved Hashtag filled with id set from database
     */
    public Hashtag save(Hashtag hashtag) {
        return this.hashtagDao.save(hashtag);
    }

    /**
     * Function to retrieve all Hashtags stored in the database
     *
     * @return a collection of Hashtags found in the database
     */
    public List<Hashtag> getAll() {
        return this.hashtagDao.getAll();
    }

    /**
     * Function to update a given Hashtag in the database
     *
     * @param hashtag the Hashtag object that needs to be updated
     * @return the updated Hashtag object
     */
    public Hashtag update(Hashtag hashtag) {
        return this.hashtagDao.update(hashtag);
    }

    /**
     * Function to delete a Hashtag by it's id
     *
     * @param id the id of the Hashtag that needs to be deleted
     */
    public void deleteById(Long id) {
        this.hashtagDao.deleteById(id);
    }

    /**
     * Function to retrieve the amount of Hashtags stored in the database
     *
     * @return the amount of Hashtags
     */
    public Long countAll() {
        return this.hashtagDao.countAll();
    }

    /**
     * Function to find a hashtag by it's id
     *
     * @param id of the hashtag you are looking for
     * @return the Hashtag with the given id
     */
    public Hashtag findById(Long id) {
        return this.hashtagDao.findById(id);
    }

    /**
     * Function to find a Hashtag by it's unique subject
     *
     * @param subject the subject the hashtag has to have (e.g. #DonaldTrump)
     * @return the Hashtag containing this unique subject
     */
    public Hashtag findBySubject(String subject) {
        return this.hashtagDao.findBySubject(subject);
    }

    /**
     * Function to convert all hashtags to a JsonObject
     *
     * @param hashtags is a list of Hashtags that need to be converted
     * @return the list of converted Hashtags (JsonObjects)
     */
    public List<JsonObject> convertAllToJson(List<Hashtag> hashtags) {
        List<JsonObject> convertedHashtags = new ArrayList<JsonObject>();
        for (Hashtag hashtag : hashtags) {
            convertedHashtags.add(hashtag.toJson());
        }
        return convertedHashtags;
    }
}
