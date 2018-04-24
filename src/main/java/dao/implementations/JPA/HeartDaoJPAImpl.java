package dao.implementations.JPA;

import dao.interfaces.HeartDao;
import domain.Heart;
import domain.JPA;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 * Created by Martijn van der Pol on 12-03-18
 **/
@Stateless
@JPA
public class HeartDaoJPAImpl extends GenericDaoJPAImpl<Heart> implements HeartDao {

    public HeartDaoJPAImpl() {

    }

    public HeartDaoJPAImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
