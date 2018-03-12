package dao.implementations.JPA;

import dao.interfaces.UserProfileDao;
import domain.JPA;
import domain.UserProfile;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 * Created by Martijn van der Pol on 12-03-18
 **/
@Stateless
@JPA
public class UserProfileDaoJPAImpl extends GenericDaoJPAImpl<UserProfile> implements UserProfileDao {

    public UserProfileDaoJPAImpl() {

    }

    public UserProfileDaoJPAImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}