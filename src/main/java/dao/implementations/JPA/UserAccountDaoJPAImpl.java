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

    public UserAccount findByUsername(String username) {
        UserAccount userAccount = (UserAccount) this.entityManager.createNamedQuery("UserAccount.findByUsername")
                .setParameter("username", username)
                .getSingleResult();

        if (userAccount != null) {
            return userAccount;
        }

        return null;
    }

    public UserAccount findByCredentials(String username, String password) {
        UserAccount userAccount = (UserAccount) this.entityManager.createNamedQuery("UserAccount.findByCredentials")
                .setParameter("username", username)
                .setParameter("password", password)
                .getSingleResult();

        if (userAccount != null) {
            return userAccount;
        }

        return null;
    }

}
