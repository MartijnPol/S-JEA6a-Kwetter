package dao;

import dao.implementations.JPA.UserAccountDaoJPAImpl;
import dao.interfaces.UserAccountDao;
import domain.UserAccount;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;

/**
 * Created by Martijn van der Pol on 01-03-18
 **/

@RunWith(MockitoJUnitRunner.class)
public class UserAccountDoaJPATest {

    UserAccountDao userAccountDao;

    @Mock
    private EntityManager mockEntityManager;

    @Before
    public void init() {
        this.userAccountDao = new UserAccountDaoJPAImpl(mockEntityManager);
    }

    @Test
    public void createTest() {
        UserAccount userAccount = new UserAccount("MartijnPol", "1234", "martijn.pol@hotmail.com");
        this.userAccountDao.create(userAccount);
        assertEquals(new Long(1), this.userAccountDao.countAll());
    }

    @Test
    public void findByIdTest() {
        UserAccount userAccount = new UserAccount("MartijnPol", "1234", "martijn.pol@hotmail.com");
        userAccount.setId(1L);

        UserAccount foundUserAccount = this.userAccountDao.findById(1L);
    }

}
