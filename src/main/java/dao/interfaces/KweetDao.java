package dao.interfaces;

import domain.Kweet;
import domain.UserProfile;
import event.KweetEvent;

import javax.enterprise.event.Observes;
import java.util.List;

/**
 * Created by Martijn van der Pol on 01-03-18
 **/
public interface KweetDao extends GenericDao<Kweet> {

    List<Kweet> findAllKweetsByMessage(String message);

    List<Kweet> findAllKweetsBySender(Long senderId);

    List<Kweet> findAllKweetsByHashtagSubject(String subject);

    List<Kweet> findAllKweetsFromFollowers(UserProfile userProfile);

    void addKweetEvent(@Observes KweetEvent kweetEvent);

}
