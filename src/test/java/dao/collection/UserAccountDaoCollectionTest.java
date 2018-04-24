package dao.collection;

import dao.implementations.collection.UserAccountDaoCollectionImpl;
import dao.interfaces.UserAccountDao;
import domain.UserAccount;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Martijn van der Pol on 01-03-18
 **/
public class UserAccountDaoCollectionTest {

    UserAccountDao accountDao;
    private UserAccount userAccount;

    @Before
    public void init() {
        this.accountDao = new UserAccountDaoCollectionImpl();
        this.userAccount = new UserAccount("test", "1234", "test@hotmail.com");
        this.userAccount.setId(1L);
    }

    @Test
    public void createTest() {
        this.accountDao.save(userAccount);
        assertEquals(new Long(1), this.accountDao.countAll());
    }

    @Test
    public void findByIdTest() {
        this.accountDao.save(userAccount);
        UserAccount foundUserAccount = this.accountDao.findById(1L);
        assertEquals(userAccount, foundUserAccount);
    }

    @Test
    public void countAllTest() {
        UserAccount firstUserAccount = new UserAccount("First", "1234", "first@hotmail.com");
        UserAccount secondUserAccount = new UserAccount("Second", "1234", "second@hotmail.com");

        this.accountDao.save(firstUserAccount);
        this.accountDao.save(secondUserAccount);

        assertEquals(new Long(2), this.accountDao.countAll());
    }

    @Test
    public void updateTest() {
        this.accountDao.save(userAccount);
        assertEquals("test", this.accountDao.findById(1L).getUsername());

        UserAccount updatedAccount = this.accountDao.findById(1L);
        updatedAccount.setUsername("hello_world");
        this.accountDao.update(updatedAccount);
        assertEquals("hello_world", this.accountDao.findById(1L).getUsername());
    }

    @Test
    public void deleteTest() {
        this.accountDao.save(userAccount);
        assertEquals(new Long(1), this.accountDao.countAll());

        this.accountDao.deleteById(1L);
        assertEquals(new Long(0), this.accountDao.countAll());
    }

}
