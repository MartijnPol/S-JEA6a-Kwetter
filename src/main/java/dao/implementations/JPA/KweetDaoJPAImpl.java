package dao.implementations.JPA;

import dao.interfaces.KweetDao;
import domain.JPA;
import domain.Kweet;
import domain.UserProfile;
import event.KweetEvent;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Martijn van der Pol on 01-03-18
 **/
@Stateless
@JPA
public class KweetDaoJPAImpl extends GenericDaoJPAImpl<Kweet> implements KweetDao {

    public KweetDaoJPAImpl() {

    }

    public KweetDaoJPAImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Kweet> findAllKweetsByMessage(String message) {
        return this.entityManager.createNamedQuery("Kweet.findAllKweetsByMessage")
                .setParameter("message", "%" + message + "%").getResultList();
    }

    public List<Kweet> findAllKweetsBySender(UserProfile sender) {
        return this.entityManager.createNamedQuery("Kweet.findAllKweetsBySender")
                .setParameter("sender", sender).getResultList();
    }

    public void addKweetEvent(@Observes KweetEvent kweetEvent) {
        Kweet kweet = kweetEvent.getKweet();
        entityManager.persist(kweet);
    }

}
