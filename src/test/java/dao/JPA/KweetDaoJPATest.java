package dao.JPA;

import dao.implementations.JPA.KweetDaoJPAImpl;
import dao.implementations.JPA.UserAccountDaoJPAImpl;
import dao.interfaces.KweetDao;
import dao.interfaces.UserAccountDao;
import domain.Kweet;
import domain.UserAccount;
import org.junit.Before;
import org.junit.Test;
import util.DatabaseCleaner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

/**
 * Created by Martijn van der Pol on 12-03-18
 **/
public class KweetDaoJPATest {

    private KweetDao kweetDao;
    private UserAccountDao userAccountDao;

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("KwetterTestPU");
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;
    private DatabaseCleaner databaseCleaner;

    @Before
    public void init() {

        try {
            this.databaseCleaner = new DatabaseCleaner(entityManagerFactory.createEntityManager());
            this.databaseCleaner.clean();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.entityManager = this.entityManagerFactory.createEntityManager();
        this.kweetDao = new KweetDaoJPAImpl(this.entityManager);
        this.userAccountDao = new UserAccountDaoJPAImpl(this.entityManager);
        this.entityTransaction = entityManager.getTransaction();
    }

    @Test
    public void createTest() {
        UserAccount userAccount = new UserAccount("MartijnPol", "1234", "martijn.pol@hotmail.com");

        entityTransaction.begin();
        this.userAccountDao.save(userAccount);
        entityTransaction.commit();

        Kweet kweet = new Kweet(userAccount.getUserProfile(), "TestMessage", null, null);

        entityTransaction.begin();
        this.kweetDao.save(kweet);
        entityTransaction.commit();

        assertEquals(new Long(1), this.kweetDao.countAll());
    }

    @Test
    public void findKweetByUserId() {
        UserAccount userAccount = new UserAccount("MartijnPol", "1234", "martijn.pol@hotmail.com");
        Kweet kweet = new Kweet(userAccount.getUserProfile(), "First kweet!", null, null);

        entityTransaction.begin();
        this.userAccountDao.save(userAccount);
        entityTransaction.commit();

        entityTransaction.begin();
        this.kweetDao.save(kweet);
        entityTransaction.commit();

        assertEquals("First kweet!", this.kweetDao.findAllKweetsBySenderId(1L).get(0).getMessage());
    }

    @Test
    public void findUserProfileTest() {
        UserAccount userAccount = new UserAccount("MartijnPol", "1234", "martijn.pol@hotmail.com");
        userAccount.getUserProfile().setBiography("Martijn zijn bio.");

        entityTransaction.begin();
        this.userAccountDao.save(userAccount);
        entityTransaction.commit();

        Kweet kweet = new Kweet(userAccount.getUserProfile(), "TestMessage", null, null);

        entityTransaction.begin();
        this.kweetDao.save(kweet);
        entityTransaction.commit();

        assertEquals("Martijn zijn bio.", this.kweetDao.findAllKweetsBySenderId(1L).get(0).getSender().getBiography());
        assertEquals("MartijnPol", this.kweetDao.findAllKweetsBySenderId(1L).get(0).getSender().getUserAccount().getUsername());
    }

}
