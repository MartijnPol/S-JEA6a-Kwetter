package dao.implementations.collection;

import dao.interfaces.HashtagDao;
import domain.Hashtag;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martijn van der Pol on 01-03-18
 **/
public class HashtagDaoCollectionImpl implements HashtagDao {

    private List<Hashtag> hashtagList;

    /**
     * Constructor for the HashtagDaoCollectionImpl
     */
    public HashtagDaoCollectionImpl() {
        this.hashtagList = new ArrayList<Hashtag>();
    }

    //<editor-fold desc="Interface methods">
    public Hashtag save(Hashtag hashtag) {
        this.hashtagList.add(hashtag);
        return hashtag;
    }

    public void deleteById(Long id) {
        for(Hashtag hashtag : this.hashtagList) {
            if (hashtag.getId().equals(id)) {
                this.hashtagList.remove(hashtag);
            }
        }
    }

    public void delete(Hashtag hashtag) {
        throw new NotImplementedException();
    }

    public Hashtag findById(Long id) {
        for (Hashtag hashtag : this.hashtagList) {
            if (hashtag.getId().equals(id)) {
                return hashtag;
            }
        }
        return null;
    }

    public Hashtag update(Hashtag newHashtag) {
        for(Hashtag oldHashtag : this.hashtagList) {
            if (oldHashtag.getId().equals(newHashtag.getId())) {
                this.hashtagList.remove(oldHashtag);
                this.hashtagList.add(newHashtag);
            }
        }
        return newHashtag;
    }

    public List<Hashtag> getAll() {
        return this.hashtagList;
    }

    public Long countAll() {
        return new Long(this.hashtagList.size());
    }
    //</editor-fold>

    //<editor-fold desc="Custom methods">
    public Hashtag findBySubject(String subject) {
        for (Hashtag hashtag : this.hashtagList) {
            if (hashtag.getSubject().equals(subject)) {
                return hashtag;
            }
        }
        return null;
    }

    //</editor-fold>
}
