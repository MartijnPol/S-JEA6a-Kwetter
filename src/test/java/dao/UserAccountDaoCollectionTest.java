package dao;

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

    @Before
    public void init() {
        this.accountDao = new UserAccountDaoCollectionImpl();
    }

    @Test
    public void createTest() {
        UserAccount userAccount = new UserAccount("test", "1234", "test@hotmail.com");
        this.accountDao.create(userAccount);
        assertEquals(new Long(1), this.accountDao.countAll());
    }

    @Test
    public void findByIdTest() {
        UserAccount userAccount = new UserAccount("test", "1234", "test@hotmail.com");
        userAccount.setId(1L);

        this.accountDao.create(userAccount);
        UserAccount foundUserAccount = this.accountDao.findById(1L);
        assertEquals(userAccount, foundUserAccount);
    }

    @Test
    public void countAllTest() {
        UserAccount firstUserAccount = new UserAccount("First", "1234", "first@hotmail.com");
        UserAccount secondUserAccount = new UserAccount("Second", "1234", "second@hotmail.com");

        this.accountDao.create(firstUserAccount);
        this.accountDao.create(secondUserAccount);

        assertEquals(new Long(2), this.accountDao.countAll());
    }

    @Test
    public void updateTest() {
        UserAccount userAccount = new UserAccount("test", "1234", "test@hotmail.com");
        userAccount.setId(1L);
        this.accountDao.create(userAccount);
        assertEquals("test", this.accountDao.findById(1L).getUsername());

        UserAccount updatedAccount = this.accountDao.findById(1L);
        updatedAccount.setUsername("hello_world");
        this.accountDao.update(updatedAccount);
        assertEquals("hello_world", this.accountDao.findById(1L).getUsername());
    }

    @Test
    public void deleteTest() {
        UserAccount userAccount = new UserAccount("test", "1234", "test@hotmail.com");
        userAccount.setId(1L);
        this.accountDao.create(userAccount);
        assertEquals(new Long(1), this.accountDao.countAll());

        this.accountDao.deleteById(1L);
        assertEquals(new Long(0), this.accountDao.countAll());
    }

}
