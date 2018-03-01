package martijn.kwetter.dao.implementations;

import martijn.kwetter.dao.interfaces.UserAccountDao;
import martijn.kwetter.domain.UserAccount;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martijn van der Pol on 01-03-18
 **/
public class UserAccountDaoCollectionImpl implements UserAccountDao {

    private List<UserAccount> userAccountList;

    public UserAccountDaoCollectionImpl() {
        this.userAccountList = new ArrayList<UserAccount>();
    }

    public UserAccount create(UserAccount userAccount) {
        this.userAccountList.add(userAccount);
        return userAccount;
    }

    public void delete(long id) {
        for (UserAccount account : this.userAccountList) {
            if (account.getId().equals(id)) {
                this.userAccountList.remove(account);
            }
        }
    }

    public UserAccount findById(long id) {
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

    public int count() {
        return this.userAccountList.size();
    }
}
