package dao.implementations.JPA;

import dao.interfaces.UserGroupDao;
import domain.JPA;
import domain.UserGroup;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by Martijn van der Pol on 29-03-18
 **/

@Stateless
@JPA
public class UserGroupDaoJPAImpl extends GenericDaoJPAImpl<UserGroup> implements UserGroupDao {

    public UserGroupDaoJPAImpl() {
    }

    public UserGroup findByName(String name) {
        return (UserGroup) this.entityManager.createNamedQuery("UserGroup.findByName")
                .setParameter("name", name).getSingleResult();
    }

    public List<UserGroup> findByUsername(String username) {
        return this.entityManager.createNamedQuery("UserGroup.findByUsername")
                .setParameter("username", username).getResultList();
    }
    
}
