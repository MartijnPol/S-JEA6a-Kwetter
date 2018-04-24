package dao.JPA;

import dao.implementations.JPA.UserAccountDaoJPAImpl;
import dao.implementations.JPA.UserProfileDaoJPAImpl;
import dao.interfaces.UserAccountDao;
import dao.interfaces.UserProfileDao;
import domain.Kweet;
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
 * Created by Martijn van der Pol on 12-03-18
 **/
public class UserProfileDaoJPATest {

    private UserAccountDao userAccountDao;
    private UserProfileDao userProfileDao;

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("KwetterTestPU");
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;
    private DatabaseCleaner databaseCleaner;

    @Before
    public void before() {
        this.databaseCleaner = new DatabaseCleaner(this.entityManagerFactory.createEntityManager());
        this.databaseCleaner.clean();

        this.entityManager = this.entityManagerFactory.createEntityManager();
        this.userAccountDao = new UserAccountDaoJPAImpl(this.entityManager);
        this.userProfileDao = new UserProfileDaoJPAImpl(this.entityManager);
        this.entityTransaction = entityManager.getTransaction();
    }

    @Test
    public void getKweetsFromUserProfileTest() {
        UserAccount userAccount = new UserAccount("MartijnPol", "1234", "martijn.pol@hotmail.com");
        userAccount.getUserProfile().setFirstName("Martijn");
        userAccount.getUserProfile().setLastName("van der Pol");
        userAccount.getUserProfile().setBiography("Hoi mijn naam is Martijn");

        userAccount.getUserProfile().addKweet(new Kweet(userAccount.getUserProfile(), "Hello!"));

        entityTransaction.begin();
        this.userAccountDao.save(userAccount);
        entityTransaction.commit();

        assertEquals(1, this.userProfileDao.findById(1L).getKweets().size());
    }

}
