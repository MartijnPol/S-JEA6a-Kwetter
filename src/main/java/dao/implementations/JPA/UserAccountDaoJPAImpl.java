package dao.implementations.JPA;

import dao.interfaces.UserAccountDao;
import domain.JPA;
import domain.UserAccount;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 * Created by Martijn van der Pol on 01-03-18
 **/
@Stateless
@JPA
public class UserAccountDaoJPAImpl extends GenericDaoJPAImpl<UserAccount> implements UserAccountDao {

    public UserAccountDaoJPAImpl() {

    }

    public UserAccountDaoJPAImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
