package martijn.kwetter.test;

import martijn.kwetter.dao.implementations.UserAccountDaoJPAImpl;
import martijn.kwetter.dao.interfaces.UserAccountDao;
import martijn.kwetter.domain.UserAccount;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Martijn van der Pol on 01-03-18
 **/

public class UserAccountDoaJPATest {

    UserAccountDao accountDao;

    @Before
    public void init() {
        this.accountDao = new UserAccountDaoJPAImpl();
    }

    @Test
    public void testCreateUserAccount() {
        UserAccount userAccount = new UserAccount("MartijnPol", "1234", "martijn.pol@hotmail.com");
        this.accountDao.create(userAccount);
    }

}
