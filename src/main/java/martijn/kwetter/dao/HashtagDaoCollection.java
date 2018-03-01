package martijn.kwetter.dao;

import martijn.kwetter.domain.Hashtag;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martijn van der Pol on 01-03-18
 **/
public class HashtagDaoCollection implements GenericDao<Hashtag> {

    private List<Hashtag> hashtagList;

    /**
     * Constructor for the HashtagDaoCollection
     */
    public HashtagDaoCollection() {
        this.hashtagList = new ArrayList<Hashtag>();
    }

    //<editor-fold desc="Interface methods">
    public Hashtag create(Hashtag hashtag) {
        this.hashtagList.add(hashtag);
        return hashtag;
    }

    public void delete(long id) {
        for(Hashtag hashtag : this.hashtagList) {
            if (hashtag.getId().equals(id)) {
                this.hashtagList.remove(hashtag);
            }
        }
    }

    public Hashtag findById(long id) {
        Hashtag foundHashtag = null;
        for (Hashtag hashtag : this.hashtagList) {
            if (hashtag.getId().equals(id)) {
                foundHashtag = hashtag;
            }
        }
        return foundHashtag;
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
    //</editor-fold>

    //<editor-fold desc="Custom methods">
    public List<Hashtag> getAllBySubject(String subject) {

        List<Hashtag> foundHashtags = new ArrayList<Hashtag>();

        for (Hashtag hashtag : this.hashtagList) {
            if (hashtag.getSubject().equals(subject)) {
                foundHashtags.add(hashtag);
            }
        }

        return foundHashtags;
    }
    //</editor-fold>
}
