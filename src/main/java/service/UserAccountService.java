package service;

import dao.interfaces.UserAccountDao;
import domain.JPA;
import domain.UserAccount;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Martijn van der Pol on 02-03-18
 **/
@Stateless
public class UserAccountService {

    @Inject @JPA
    private UserAccountDao userAccountDao;

    public UserAccountService() {

    }

    public void create(UserAccount userAccount) {
        userAccountDao.create(userAccount);
    }

    public List<UserAccount> getAll() { return userAccountDao.getAll(); }

    public UserAccount findById(long id) {
        return userAccountDao.findById(id);
    }

    public Long countAll() {
        return userAccountDao.countAll();
    }
}
