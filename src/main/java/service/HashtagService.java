package service;

import dao.interfaces.HashtagDao;
import domain.Hashtag;
import domain.JPA;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Martijn van der Pol on 12-03-18
 **/
@Stateless
public class HashtagService {

    @Inject
    @JPA
    private HashtagDao hashtagDao;

    public HashtagService() {

    }

    /**
     * Function to save a Hashtag to the database
     *
     * @param hashtag the hashtag that needs to be saved
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
     * Function to retrieve all Hashtags stored in the database
     *
     * @return the amount of Hashtags
     */
    public Long count() {
        return this.hashtagDao.countAll();
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

}
