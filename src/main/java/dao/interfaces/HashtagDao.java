package dao.interfaces;

import domain.Hashtag;

/**
 * Created by Martijn van der Pol on 01-03-18
 **/
public interface HashtagDao extends GenericDao<Hashtag> {

    Hashtag findBySubject(String subject);

}
