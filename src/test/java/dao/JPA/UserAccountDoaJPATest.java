package dao.JPA;

import dao.implementations.JPA.UserAccountDaoJPAImpl;
import dao.interfaces.UserAccountDao;
import domain.UserAccount;
import org.junit.Before;
import org.junit.Test;
import util.DatabaseCleaner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.Assert.assertEquals;

/**
 * Created by Martijn van der Pol on 01-03-18
 **/
public class UserAccountDoaJPATest {

    private UserAccountDao userAccountDao;
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("KwetterTestPU");
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;
    private DatabaseCleaner databaseCleaner;

    @Before
    public void init() {
        this.databaseCleaner = new DatabaseCleaner(entityManagerFactory.createEntityManager());
        this.databaseCleaner.clean();

        this.entityManager = this.entityManagerFactory.createEntityManager();
        this.userAccountDao = new UserAccountDaoJPAImpl(this.entityManager);
        this.entityTransaction = entityManager.getTransaction();
    }

    @Test
    public void createTest() {
        UserAccount userAccount = new UserAccount("MartijnPol", "1234", "martijn.pol@hotmail.com");

        entityTransaction.begin();
        this.userAccountDao.save(userAccount);
        entityTransaction.commit();

        assertEquals(new Long(1), this.userAccountDao.countAll());
    }

    @Test
    public void findByIdTest() {
        UserAccount userAccount = new UserAccount("MartijnPol", "1234", "martijn.pol@hotmail.com");

        entityTransaction.begin();
        this.userAccountDao.save(userAccount);
        entityTransaction.commit();

        assertEquals(userAccount, this.userAccountDao.findById(1L));
    }

    @Test
    public void updateTest() {
        UserAccount userAccount = new UserAccount("MartijnPol", "1234", "martijn.pol@hotmail.com");

        entityTransaction.begin();
        this.userAccountDao.save(userAccount);
        entityTransaction.commit();

        assertEquals("MartijnPol", this.userAccountDao.findById(1L).getUsername());

        userAccount.setUsername("Harry4Life");

        entityTransaction.begin();
        this.userAccountDao.update(userAccount);
        entityTransaction.commit();

        assertEquals("Harry4Life", this.userAccountDao.findById(1L).getUsername());
    }

    @Test
    public void deleteTest() {
        UserAccount userAccount = new UserAccount("MartijnPol", "1234", "martijn.pol@hotmail.com");

        entityTransaction.begin();
        this.userAccountDao.save(userAccount);
        entityTransaction.commit();

        assertEquals(new Long(1), this.userAccountDao.countAll());

        entityTransaction.begin();
        this.userAccountDao.deleteById(userAccount.getId());
        entityTransaction.commit();

        assertEquals(new Long(0), this.userAccountDao.countAll());
    }

    @Test
    public void getUserProfileFromUserAccountTest() {
        UserAccount userAccount = new UserAccount("MartijnPol", "1234", "martijn.pol@hotmail.com");
        userAccount.getUserProfile().setBiography("Mijn bio.");

        entityTransaction.begin();
        this.userAccountDao.save(userAccount);
        entityTransaction.commit();

        assertEquals("Mijn bio.", this.userAccountDao.findById(1L).getUserProfile().getBiography());
    }

    @Test
    public void getUserAccountFromUserProfileTest() {
        UserAccount userAccount = new UserAccount("MartijnPol", "1234", "martijn.pol@hotmail.com");

        entityTransaction.begin();
        this.userAccountDao.save(userAccount);
        entityTransaction.commit();

        assertEquals("martijn.pol@hotmail.com", this.userAccountDao.findById(1L).getUserProfile().getUserAccount().getMailAddress());
    }
}
