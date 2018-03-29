package dao.implementations.JPA;

import dao.interfaces.UserGroupDao;
import domain.JPA;
import domain.UserGroup;

import javax.ejb.Stateless;

/**
 * Created by Martijn van der Pol on 29-03-18
 **/

@Stateless
@JPA
public class UserGroupDaoJPAImpl extends GenericDaoJPAImpl<UserGroup> implements UserGroupDao {

    public UserGroupDaoJPAImpl() {
    }

}
