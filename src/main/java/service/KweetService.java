package service;

import dao.interfaces.KweetDao;
import domain.JPA;
import domain.Kweet;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Martijn van der Pol on 09-03-18
 **/

@Stateless
public class KweetService {

    @Inject
    @JPA
    private KweetDao kweetDao;

    public KweetService() {

    }

    /**
     * Function to save a Kweet to the database
     *
     * @param kweet Kweet object to save in the database
     */
    public void addKweet(Kweet kweet) {
        kweetDao.save(kweet);
    }

    /**
     * Function to find all kweets of a specific user
     *
     * @param id id of the user
     * @return all kweets from the given user
     */
    public List<Kweet> findKweetsById(Long id) {
        return kweetDao.findAllKweetsBySenderId(id);
    }

}