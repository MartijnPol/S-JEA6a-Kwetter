package service;

import dao.interfaces.HeartDao;
import domain.Heart;
import domain.JPA;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Martijn van der Pol on 12-03-18
 **/
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
     * @param heartDao
     */
    public HeartService(HeartDao heartDao) {
        this.heartDao = heartDao;
    }

    /**
     * Function to save a Heart to the database
     *
     * @param heart the heart that needs to be saved
     */
    public Heart save(Heart heart) {
        return this.heartDao.save(heart);
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
        return this.heartDao.findById(id);
    }

    /**
     * Function to update a given Heart in the database
     *
     * @param heart the Heart object that needs to be updated
     * @return the updated Heart object
     */
    public Heart update(Heart heart) {
        return this.heartDao.update(heart);
    }

    /**
     * Function to delete a Heart by it's id
     *
     * @param id the id of the Heart that needs to be deleted
     */
    public void deleteById(Long id) {
        this.heartDao.deleteById(id);
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
