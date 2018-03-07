package dao.implementations;

import dao.interfaces.KweetDao;
import domain.Kweet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martijn van der Pol on 01-03-18
 **/
public class KweetDaoCollectionImpl implements KweetDao {

    private List<Kweet> kweetList;

    /**
     * Constructor for the KweetDaoCollectionImpl
     */
    public KweetDaoCollectionImpl() {
        this.kweetList = new ArrayList<Kweet>();
    }

    //<editor-fold desc="Interface methods">
    public Kweet create(Kweet kweet) {
        this.kweetList.add(kweet);
        return kweet;
    }

    public void delete(long id) {
        for (Kweet kweet : this.kweetList) {
            if (kweet.getId().equals(id)) {
                this.kweetList.remove(kweet);
            }
        }
    }

    public Kweet findById(long id) {
        for (Kweet kweet : this.kweetList) {
            if (kweet.getId().equals(id)) {
                return kweet;
            }
        }
        return null;
    }

    public Kweet update(Kweet newKweet) {
        for (Kweet oldKweet : this.kweetList) {
            if (oldKweet.getId().equals(newKweet.getId())) {
                this.kweetList.remove(oldKweet);
                this.kweetList.add(newKweet);
            }
        }
        return newKweet;
    }

    public List<Kweet> getAll() {
        return this.kweetList;
    }
    //</editor-fold>
}
