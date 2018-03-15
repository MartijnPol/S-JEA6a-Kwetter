package dao.implementations.JPA;

import dao.interfaces.LoggingDao;
import domain.JPA;
import domain.Log;

import javax.ejb.Stateless;

/**
 * Created by Martijn van der Pol on 15-03-18
 **/

@Stateless
@JPA
public class LoggingDaoJPAImpl extends GenericDaoJPAImpl<Log> implements LoggingDao {

    public LoggingDaoJPAImpl() {

    }

}
