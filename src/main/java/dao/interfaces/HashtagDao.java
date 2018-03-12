package dao.interfaces;

import domain.Hashtag;

import java.util.List;

/**
 * Created by Martijn van der Pol on 01-03-18
 **/
public interface HashtagDao extends GenericDao<Hashtag> {

    List<Hashtag> getAllBySubject(String subject);

}