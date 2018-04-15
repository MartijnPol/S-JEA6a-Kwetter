package dao.implementations.JPA;

import dao.interfaces.HashtagDao;
import domain.Hashtag;
import domain.JPA;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 * Created by Martijn van der Pol on 12-03-18
 **/
@Stateless
@JPA
public class HashtagDaoJPAImpl extends GenericDaoJPAImpl<Hashtag> implements HashtagDao {

    public HashtagDaoJPAImpl() {

    }

    public HashtagDaoJPAImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Hashtag findBySubject(String subject) {
        return (Hashtag) this.entityManager.createNamedQuery("Hashtag.findBySubject")
                .setParameter("subject", subject).getSingleResult();
    }

}
