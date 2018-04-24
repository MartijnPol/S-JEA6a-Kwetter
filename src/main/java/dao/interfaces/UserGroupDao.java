package dao.interfaces;

import domain.UserGroup;

import java.util.List;

/**
 * Created by Martijn van der Pol on 29-03-18
 **/
public interface UserGroupDao extends GenericDao<UserGroup> {

    UserGroup findByName(String name);

    List<UserGroup> findByUsername(String username);

}
