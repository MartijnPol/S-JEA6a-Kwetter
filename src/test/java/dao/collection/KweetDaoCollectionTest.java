package dao.collection;

import dao.implementations.collection.KweetDaoCollectionImpl;
import dao.interfaces.KweetDao;
import domain.Kweet;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Martijn van der Pol on 01-03-18
 **/
public class KweetDaoCollectionTest {

    KweetDao kweetDao;
    private Kweet kweet;

    @Before
    public void init() {
        this.kweetDao = new KweetDaoCollectionImpl();
        Kweet kweet = new Kweet();
        this.kweet = kweet;
    }

    @Test
    public void createTest() {

    }

    @Test
    public void findByIdTest() {

    }

    @Test
    public void countAllTest() {

    }

    @Test
    public void updateTest() {

    }

    @Test
    public void deleteTest() {

    }

}
