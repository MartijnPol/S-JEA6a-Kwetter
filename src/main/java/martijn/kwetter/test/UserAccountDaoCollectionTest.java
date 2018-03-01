package martijn.kwetter.test;

import martijn.kwetter.dao.implementations.UserAccountDaoCollectionImpl;
import martijn.kwetter.dao.interfaces.UserAccountDao;
import martijn.kwetter.domain.UserAccount;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Martijn van der Pol on 01-03-18
 **/
public class UserAccountDaoCollectionTest {

    UserAccountDao accountDao;

    @Before
    public void init() {
        this.accountDao = new UserAccountDaoCollectionImpl();
    }

    @Test
    public void testCreateUserAccount() {
        UserAccount userAccount = new UserAccount("MartijnPol", "1234", "martijn.pol@hotmail.com");
        this.accountDao.create(userAccount);
        Assert.assertEquals(1, accountDao.count());
    }

}
