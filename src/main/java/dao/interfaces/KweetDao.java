package dao.interfaces;

import domain.Kweet;

import java.util.List;

/**
 * Created by Martijn van der Pol on 01-03-18
 **/
public interface KweetDao extends GenericDao<Kweet> {

    List<Kweet> findAllKweetsBySenderId(Long id);

}
