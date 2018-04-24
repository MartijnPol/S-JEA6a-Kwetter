package dao.implementations.collection;

import dao.interfaces.KweetDao;
import domain.Kweet;
import domain.UserProfile;
import event.KweetEvent;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.enterprise.event.Observes;
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
    public Kweet save(Kweet kweet) {
        this.kweetList.add(kweet);
        return kweet;
    }

    public void deleteById(Long id) {
        for (Kweet kweet : this.kweetList) {
            if (kweet.getId().equals(id)) {
                this.kweetList.remove(kweet);
            }
        }
    }

    public void delete(Kweet kweet) {
        throw new NotImplementedException();
    }

    public Kweet findById(Long id) {
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

    public Long countAll() {
        return (long) this.kweetList.size();
    }

    public List<Kweet> findAllKweetsByMessage(String message) {
        List<Kweet> matchingKweets = new ArrayList<Kweet>();
        for (Kweet kweet : this.kweetList) {
            if (kweet.getMessage().contains(message)) {
                matchingKweets.add(kweet);
            }
        }
        return matchingKweets;
    }

    public List<Kweet> findAllKweetsBySender(Long senderId) {
        throw new NotImplementedException();
    }

    public List<Kweet> findAllKweetsByHashtagSubject(String subject) {
        throw new NotImplementedException();
    }

    public List<Kweet> findAllKweetsFromFollowing(UserProfile profile) {
        throw new NotImplementedException();
    }

    public void addKweetEvent(@Observes KweetEvent kweetEvent) {
        Kweet kweet = kweetEvent.getKweet();
        this.kweetList.add(kweet);
    }

    //</editor-fold>
}
