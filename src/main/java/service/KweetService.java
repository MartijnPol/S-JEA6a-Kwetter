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
     */
    public Kweet update(Kweet kweet) {
        return this.kweetDao.update(kweet);
    }

    /**
     * Function to find a specific Kweet by it's id
     * @param id is the id from the specific Kweet that needs to be retrieved
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

}