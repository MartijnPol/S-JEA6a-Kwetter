package dao.implementations.collection;

import dao.interfaces.UserAccountDao;
import domain.UserAccount;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Martijn van der Pol on 01-03-18
 **/
public class UserAccountDaoCollectionImpl implements UserAccountDao {

    private List<UserAccount> userAccountList;

    public UserAccountDaoCollectionImpl() {
        this.userAccountList = new ArrayList<UserAccount>();
    }

    public UserAccount save(UserAccount userAccount) {
        this.userAccountList.add(userAccount);
        return userAccount;
    }

    public void deleteById(Long id) {
        Iterator<UserAccount> iterator = this.userAccountList.iterator();
        while (iterator.hasNext()) {
            UserAccount userAccount = iterator.next();
            if (userAccount.getId().equals(id)) {
                iterator.remove();
            }
        }
    }

    public void delete(UserAccount userAccount) {
        throw new NotImplementedException();
    }

    public UserAccount findById(Long id) {
        for (UserAccount account : this.userAccountList) {
            if (account.getId().equals(id)) {
                return account;
            }
        }
        return null;
    }

    public UserAccount update(UserAccount newAccount) {
        for (UserAccount oldAccount : this.userAccountList) {
            if (oldAccount.getId().equals(newAccount.getId())) {
                this.userAccountList.remove(oldAccount);
                this.userAccountList.add(newAccount);
            }
        }
        return newAccount;
    }

    public List<UserAccount> getAll() {
        return this.userAccountList;
    }

    public Long countAll() {
        return (long) this.userAccountList.size();
    }

    public UserAccount findByUsername(String username) {
        for (UserAccount account : this.userAccountList) {
            if (account.getUsername().equals(username)) {
                return account;
            }
        }
        return null;
    }

    public UserAccount findByCredentials(String username, String password) {
        throw new NotImplementedException();
    }
}
