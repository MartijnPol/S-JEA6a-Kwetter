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

    public void addKweet(Kweet kweet) {
        kweetDao.create(kweet);
    }

    public List<Kweet> findAllKweetsBySenderId(long id) {
        return kweetDao.findAllKweetsBySenderId(id);
    }
}