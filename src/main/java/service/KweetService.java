package service;

import dao.interfaces.KweetDao;
import domain.JPA;
import domain.Kweet;

import javax.ejb.Stateless;
import javax.inject.Inject;

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
        kweetDao.create(kweet);
    }

}