package service;

import dao.interfaces.UserGroupDao;
import domain.JPA;
import domain.UserGroup;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

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

    /**
     * Updates a UserGroup
     *
     * @param userGroup
     * @return
     */
    public UserGroup update(UserGroup userGroup) {
        return this.userGroupDao.update(userGroup);
    }

    /**
     * Find a UserGroup by name
     *
     * @param name name of the UserGroup
     * @return UserGroup
     */
    public UserGroup findByName(String name) {
        return this.userGroupDao.findByName(name);
    }

    /**
     * Find UserGroups by given username
     *
     * @param username username of the User
     * @return List of all UserGroups
     */
    public List<UserGroup> findByUsername(String username) {
        return this.userGroupDao.findByUsername(username);
    }

    /**
     * Function to get all UserGroups
     *
     * @return all UserGroups
     */
    public List<UserGroup> getAll() {
        return this.userGroupDao.getAll();
    }

}
