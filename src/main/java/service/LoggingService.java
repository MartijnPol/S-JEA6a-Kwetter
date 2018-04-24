package service;

import dao.interfaces.LoggingDao;
import domain.JPA;
import domain.Log;

import javax.ejb.Stateless;
import javax.inject.Inject;

import static utils.StaticHelperFunctions.isNull;

/**
 * Created by Martijn van der Pol on 15-03-18
 **/

@Stateless
public class LoggingService {

    @Inject
    @JPA
    private LoggingDao loggingDao;

    /**
     * Empty constructor
     */
    public LoggingService() {

    }

    /**
     * Function to save the log to the database
     *
     * @param log
     * @return the Log filled with id
     */
    public Log save(Log log) {
        if (!isNull(log)) {
            return this.loggingDao.save(log);
        }
        return null;
    }

}
