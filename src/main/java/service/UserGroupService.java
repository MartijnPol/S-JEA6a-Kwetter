package service;

import dao.interfaces.UserGroupDao;
import domain.JPA;
import domain.UserGroup;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by Martijn van der Pol on 29-03-18
 **/

@Stateless
public class UserGroupService {

    @Inject
    @JPA
    private UserGroupDao userGroupDao;

    public UserGroupService() {
    }

    /**
     * Create a new UserGroupService.
     *
     * @param userGroupDao is the UserGruopDao that should communicate to the DataBase.
     */
    public UserGroupService(UserGroupDao userGroupDao) {
        this.userGroupDao = userGroupDao;
    }

    /**
     * Create a new UserGroup.
     *
     * @param userGroup is the UserGroup that has to be created.
     * @returns the created UserGroup.
     */
    public UserGroup save(UserGroup userGroup) {
        return this.userGroupDao.save(userGroup);
    }

}
