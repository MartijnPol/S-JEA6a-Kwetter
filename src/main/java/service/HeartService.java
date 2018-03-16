package service;

import dao.interfaces.HeartDao;
import domain.Heart;
import domain.JPA;
import interceptor.LoggingInterceptor;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import java.util.List;

import static utils.StaticHelperFunctions.isNull;

/**
 * Created by Martijn van der Pol on 12-03-18
 **/
@Interceptors(LoggingInterceptor.class)
@Stateless
public class HeartService {

    @Inject
    @JPA
    private HeartDao heartDao;

    /**
     * Empty constructor
     */
    public HeartService() {

    }

    /**
     * Function to set the HeartDao via constructor injection
     *
     * @param heartDao heartDao
     */
    public HeartService(HeartDao heartDao) {
        this.heartDao = heartDao;
    }

    /**
     * Function to save a Heart to the database
     *
     * @param heart the heart that needs to be saved
     * @return the Heart filled with id from database
     */
    public Heart save(Heart heart) {
        if (!isNull(heart)) {
            return this.heartDao.save(heart);
        }
        return null;
    }

    /**
     * Function to retrieve all Hearts stored in the database
     *
     * @return a collection of Hearts found in the database
     */
    public List<Heart> getAll() {
        return this.heartDao.getAll();
    }

    /**
     * Function to find a Heart by it's id
     *
     * @param id of the Heart
     * @return the Heart object with the given id
     */
    public Heart findById(Long id) {
        if (!isNull(id)) {
            return this.heartDao.findById(id);
        }
        return null;
    }

    /**
     * Function to update a given Heart in the database
     *
     * @param heart the Heart object that needs to be updated
     * @return the updated Heart object
     */
    public Heart update(Heart heart) {
        if (!isNull(heart)) {
            return this.heartDao.update(heart);
        }
        return null;
    }

    /**
     * Function to delete a Heart by it's id
     *
     * @param id the id of the Heart that needs to be deleted
     */
    public void deleteById(Long id) {
        if (!isNull(id)) {
            this.heartDao.deleteById(id);
        }
    }

    /**
     * Function to retrieve all Hearts stored in the database
     *
     * @return the amount of Hearts
     */
    public Long countAll() {
        return this.heartDao.countAll();
    }

}
