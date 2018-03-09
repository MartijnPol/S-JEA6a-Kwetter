package dao.implementations.JPA;

import dao.interfaces.UserAccountDao;
import domain.JPA;
import domain.UserAccount;

import javax.ejb.Stateless;

/**
 * Created by Martijn van der Pol on 01-03-18
 **/
@Stateless
@JPA
public class UserAccountDaoJPAImpl extends GenericDaoJPAImpl<UserAccount> implements UserAccountDao {

    public UserAccountDaoJPAImpl() {

    }

}
