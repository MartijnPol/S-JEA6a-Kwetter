package dao.JPA;

import dao.implementations.JPA.KweetDaoJPAImpl;
import dao.implementations.JPA.UserAccountDaoJPAImpl;
import dao.interfaces.KweetDao;
import dao.interfaces.UserAccountDao;
import domain.Hashtag;
import domain.Heart;
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
public class KweetDaoJPATest {

    private KweetDao kweetDao;
    private UserAccountDao userAccountDao;

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("KwetterTestPU");
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;
    private DatabaseCleaner databaseCleaner;

    @Before
    public void Before() {
        this.databaseCleaner = new DatabaseCleaner(this.entityManagerFactory.createEntityManager());
        this.databaseCleaner.clean();

        this.entityManager = this.entityManagerFactory.createEntityManager();
        this.kweetDao = new KweetDaoJPAImpl(this.entityManager);
        this.userAccountDao = new UserAccountDaoJPAImpl(this.entityManager);
        this.entityTransaction = entityManager.getTransaction();
    }

    @Test
    public void createKweetTest() {
        UserAccount userAccount = new UserAccount("MartijnPol", "1234", "martijn.pol@hotmail.com");

        entityTransaction.begin();
        this.userAccountDao.save(userAccount);
        entityTransaction.commit();

        Kweet kweet = new Kweet(userAccount.getUserProfile(), "TestMessage");

        entityTransaction.begin();
        this.kweetDao.save(kweet);
        entityTransaction.commit();

        assertEquals(new Long(1), this.kweetDao.countAll());
    }

    @Test(expected = IllegalArgumentException.class)
    public void createKweetWithMaxCharactersTest() {
        UserAccount userAccount = new UserAccount("MartijnPol", "1234", "martijn.pol@hotmail.com");

        entityTransaction.begin();
        this.userAccountDao.save(userAccount);
        entityTransaction.commit();

        Kweet kweet = new Kweet(userAccount.getUserProfile(), "TestMessageTestMessageTestMessageTestMessageTestMessageTestMessageTestMessageTestMessageTestMessageTestMessageTestMessageTestMessageTestMessageTestMessageTestMessageTestMessageTestMessage");

        entityTransaction.begin();
        this.kweetDao.save(kweet);
        entityTransaction.commit();
    }

    @Test
    public void findKweetByUserId() {
        UserAccount userAccount = new UserAccount("MartijnPol", "1234", "martijn.pol@hotmail.com");
        Kweet kweet = new Kweet(userAccount.getUserProfile(), "First kweet!");

        entityTransaction.begin();
        this.userAccountDao.save(userAccount);
        this.kweetDao.save(kweet);
        entityTransaction.commit();

        assertEquals("First kweet!", this.kweetDao.findById(1L).getMessage());
    }

    @Test
    public void findUserProfileTest() {
        UserAccount userAccount = new UserAccount("MartijnPol", "1234", "martijn.pol@hotmail.com");
        userAccount.getUserProfile().setBiography("Martijn zijn bio.");

        entityTransaction.begin();
        this.userAccountDao.save(userAccount);
        entityTransaction.commit();

        Kweet kweet = new Kweet(userAccount.getUserProfile(), "TestMessage");

        entityTransaction.begin();
        this.kweetDao.save(kweet);
        entityTransaction.commit();

        assertEquals("Martijn zijn bio.", this.kweetDao.findById(1L).getSender().getBiography());
    }

    @Test
    public void addHeartTest() {
        UserAccount userAccount = new UserAccount("MartijnPol", "1234", "martijn.pol@hotmail.com");

        entityTransaction.begin();
        this.userAccountDao.save(userAccount);
        entityTransaction.commit();

        Kweet kweet = new Kweet(userAccount.getUserProfile(), "TestMessage");

        entityTransaction.begin();
        kweet.addLike(new Heart(userAccount.getUserProfile(), kweet));
        this.kweetDao.save(kweet);
        entityTransaction.commit();

        assertEquals(1, this.kweetDao.findById(1L).getLikes().size());
    }

    @Test
    public void addHashtagTest() {
        UserAccount userAccount = new UserAccount("MartijnPol", "1234", "martijn.pol@hotmail.com");
        userAccount.getUserProfile().setFirstName("Martijn");

        entityTransaction.begin();
        this.userAccountDao.save(userAccount);
        entityTransaction.commit();

        Kweet kweet = new Kweet(userAccount.getUserProfile(), "TestMessage");

        entityTransaction.begin();
        kweet.addHashtag(new Hashtag("#Hashtag"));
        this.kweetDao.save(kweet);
        entityTransaction.commit();

        assertEquals(1, this.kweetDao.findById(1L).getHashtags().size());
    }

    @Test
    public void addMentionTest() {
        UserAccount firstUserAccount = new UserAccount("MartijnPol", "1234", "martijn.pol@hotmail.com");
        firstUserAccount.getUserProfile().setFirstName("Martijn");

        UserAccount secondUserAccount = new UserAccount("Harry", "1234", "harry@test.nl");
        secondUserAccount.getUserProfile().setFirstName("Harry");

        entityTransaction.begin();
        this.userAccountDao.save(firstUserAccount);
        this.userAccountDao.save(secondUserAccount);
        entityTransaction.commit();

        Kweet kweet = new Kweet(firstUserAccount.getUserProfile(), "TestMessage");
        kweet.addMention(secondUserAccount.getUserProfile());

        entityTransaction.begin();
        this.kweetDao.save(kweet);
        entityTransaction.commit();

        assertEquals(1, this.kweetDao.findById(1L).getMentions().size());
    }

    @Test
    public void findByMessageTest() {
        UserAccount userAccount = new UserAccount("MartijnPol", "1234", "martijn.pol@hotmail.com");

        entityTransaction.begin();
        this.userAccountDao.save(userAccount);
        entityTransaction.commit();

        Kweet kweet = new Kweet(userAccount.getUserProfile(), "Test message?");

        entityTransaction.begin();
        this.kweetDao.save(kweet);
        entityTransaction.commit();

        assertEquals("Test message?", this.kweetDao.findAllKweetsByMessage("message").get(0).getMessage());
    }

}
