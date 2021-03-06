package dao.implementations.collection;

import dao.interfaces.HeartDao;
import domain.Heart;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martijn van der Pol on 01-03-18
 **/
public class HeartDaoCollectionImpl implements HeartDao {

    private List<Heart> likeList;

    /**
     * Constructor for the LikeDaoCollectionImpl
     */
    public HeartDaoCollectionImpl() {
        this.likeList = new ArrayList<Heart>();
    }

    //<editor-fold desc="Interface methods">
    public Heart save(Heart like) {
        this.likeList.add(like);
        return like;
    }

    public void deleteById(Long id) {
        for (Heart like : this.likeList) {
            if (like.getId().equals(id)) {
                this.likeList.remove(like);
            }
        }
    }

    public void delete(Heart heart) {
        throw new NotImplementedException();
    }

    public Heart findById(Long id) {
        for (Heart like : this.likeList) {
            if (like.getId().equals(id)) {
                return like;
            }
        }
        return null;
    }

    public Heart update(Heart newLike) {
        for (Heart oldLike : this.likeList) {
            if (oldLike.getId().equals(newLike.getId())) {
                this.likeList.remove(oldLike);
                this.likeList.add(newLike);
            }
        }
        return newLike;
    }

    public List<Heart> getAll() {
        return this.likeList;
    }

    public Long countAll() {
        return new Long(this.likeList.size());
    }
    //</editor-fold>

}