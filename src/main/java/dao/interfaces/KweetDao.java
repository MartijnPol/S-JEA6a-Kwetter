package dao.interfaces;

import domain.Kweet;
import event.KweetEvent;

import javax.enterprise.event.Observes;
import java.util.List;

/**
 * Created by Martijn van der Pol on 01-03-18
 **/
public interface KweetDao extends GenericDao<Kweet> {

    List<Kweet> findAllKweetsByMessage(String message);

    void addKweetEvent(@Observes KweetEvent kweetEvent);

}
