package dao.implementations.JPA;

import dao.interfaces.KweetDao;
import domain.JPA;
import domain.Kweet;

import javax.ejb.Stateless;
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
                .setParameter("message", "%" + message + "%")
                .getResultList();
    }

}
