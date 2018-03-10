package dao.implementations.JPA;

import dao.interfaces.KweetDao;
import domain.JPA;
import domain.Kweet;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by Martijn van der Pol on 01-03-18
 **/
@Stateless
@JPA
public class KweetDaoJPAImpl extends GenericDaoJPAImpl<Kweet> implements KweetDao {

    public List<Kweet> findAllKweetsBySenderId(Long id) {
        return this.entityManager.createNamedQuery("Kweet.findAllKweetsBySenderId").getResultList();
    }
}
