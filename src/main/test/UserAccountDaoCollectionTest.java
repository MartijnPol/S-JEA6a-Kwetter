import dao.implementations.UserAccountDaoCollectionImpl;
import dao.interfaces.UserAccountDao;
import domain.UserAccount;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

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
        assertEquals(1, accountDao.count());
    }

}
