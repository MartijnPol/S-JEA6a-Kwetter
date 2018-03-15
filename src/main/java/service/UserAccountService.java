package service;

import dao.interfaces.UserAccountDao;
import domain.JPA;
import domain.UserAccount;
import interceptor.LoggingInterceptor;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

/**
 * Created by Martijn van der Pol on 12-03-18
 **/
@Interceptors(LoggingInterceptor.class)
@Stateless
public class UserAccountService {

    @Inject @JPA
    private UserAccountDao userAccountDao;

    /**
     * Empty constructor
     */
    public UserAccountService() {

    }

    /**
     * Function to set the UserAccountDao via constructor injection
     *
     * @param userAccountDao userAccountDao
     */
    public UserAccountService(UserAccountDao userAccountDao) {
        this.userAccountDao = userAccountDao;
    }

    /**
     * Function to store a UserAccount in the database
     *
     * @return the UserAccount object from the database (with id etc.)
     * @param userAccount the UserAccount object that needs to be stored in the database
     */
    public UserAccount save(UserAccount userAccount) {
        return this.userAccountDao.save(userAccount);
    }

    /**
     * Function to find a UserAccount by a given id
     *
     * @param id is the id of the UserAccount to retrieve
     * @return a UserAccount if found
     */
    public UserAccount findById(Long id) {
        return this.userAccountDao.findById(id);
    }

    /**
     * Function to find a UserAccount by a given username
     *
     * @param username the Username
     * @return the object connected with the given username
     */
    public UserAccount findByUsername(String username) {
        return this.userAccountDao.findByUsername(username);
    }


    /**
     * Function to update a given UserAccount object in the database
     *
     * @param userAccount UserAccount object that needs to be updated in the database
     * @return the updated UserAccount object
     */
    public UserAccount update(UserAccount userAccount) {
        return this.userAccountDao.update(userAccount);
    }

    /**
     * Function to delete a UserAccount from the database by it's id
     *
     * @param id is the id of the UserAccount that needs to be deleted
     */
    public void deleteById(Long id) {
        this.userAccountDao.deleteById(id);
    }

    /**
     * Function to count all UserAccount object stored in the database
     *
     * @return the amount of UserAccounts stored in the database
     */
    public Long countAll() {
        return this.userAccountDao.countAll();
    }

}




