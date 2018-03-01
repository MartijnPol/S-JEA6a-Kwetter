package martijn.kwetter.dao.interfaces;

import martijn.kwetter.domain.UserAccount;

/**
 * Created by Martijn van der Pol on 01-03-18
 **/
public interface UserAccountDao extends GenericDao<UserAccount> {

    int count();

}
