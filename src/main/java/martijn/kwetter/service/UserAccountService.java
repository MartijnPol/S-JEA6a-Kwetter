package martijn.kwetter.service;

import martijn.kwetter.dao.interfaces.UserAccountDao;
import martijn.kwetter.domain.JPA;
import martijn.kwetter.domain.UserAccount;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by Martijn van der Pol on 02-03-18
 **/

@Stateless
public class UserAccountService {

    @Inject
    @JPA
    private UserAccountDao userAccountDao;

    public UserAccountService() {

    }

    public void addUserAccount(UserAccount userAccount) {
        userAccountDao.create(userAccount);
    }

}
