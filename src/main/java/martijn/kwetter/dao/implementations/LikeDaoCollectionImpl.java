package martijn.kwetter.dao.implementations;

import martijn.kwetter.dao.interfaces.LikeDao;
import martijn.kwetter.domain.Like;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martijn van der Pol on 01-03-18
 **/
public class LikeDaoCollectionImpl implements LikeDao {

    private List<Like> likeList;

    /**
     * Constructor for the LikeDaoCollectionImpl
     */
    public LikeDaoCollectionImpl() {
        this.likeList = new ArrayList<Like>();
    }

    //<editor-fold desc="Interface methods">
    public Like create(Like like) {
        this.likeList.add(like);
        return like;
    }

    public void delete(long id) {
        for (Like like : this.likeList) {
            if (like.getId().equals(id)) {
                this.likeList.remove(like);
            }
        }
    }

    public Like findById(long id) {
        for (Like like : this.likeList) {
            if (like.getId().equals(id)) {
                return like;
            }
        }
        return null;
    }

    public Like update(Like newLike) {
        for (Like oldLike : this.likeList) {
            if (oldLike.getId().equals(newLike.getId())) {
                this.likeList.remove(oldLike);
                this.likeList.add(newLike);
            }
        }
        return newLike;
    }
    //</editor-fold>

}