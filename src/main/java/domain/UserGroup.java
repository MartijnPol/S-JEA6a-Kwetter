package domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martijn van der Pol on 25-03-18
 **/

@Entity
@NamedQueries({
        @NamedQuery(name = "UserGroup.findByName", query = "SELECT u FROM UserGroup u WHERE u.groupName = :name")
})
public class UserGroup implements Serializable {

    @Id
    private String groupName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USERGROUP_USERACCOUNT",
            joinColumns = @JoinColumn(name = "GROUPNAME",
                    referencedColumnName = "groupName"),
            inverseJoinColumns = @JoinColumn(name = "USERNAME",
                    referencedColumnName = "username"))
    private List<UserAccount> accounts;

    public UserGroup() {
        this.accounts = new ArrayList<UserAccount>();
    }

    public UserGroup(String groupName) {
        this();
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<UserAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<UserAccount> accounts) {
        this.accounts = accounts;
    }

    public void addUser(UserAccount user) {
        this.accounts.add(user);
    }

    public void addUsers(List<UserAccount> users) {
        this.accounts.addAll(users);
    }
}
