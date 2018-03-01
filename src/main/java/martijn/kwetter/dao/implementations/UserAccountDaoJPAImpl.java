package martijn.kwetter.dao.implementations;

import martijn.kwetter.dao.interfaces.UserAccountDao;
import martijn.kwetter.domain.UserAccount;

/**
 * Created by Martijn van der Pol on 01-03-18
 **/
public class UserAccountDaoJPAImpl extends GenericDaoJPAImpl<UserAccount> implements UserAccountDao {

    public int count() {
        return 0;
    }
}
