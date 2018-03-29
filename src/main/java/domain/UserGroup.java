package domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martijn van der Pol on 25-03-18
 **/

@Entity
public class UserGroup implements Serializable {

    @Id
    private String groupName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USERGROUP_USERACCOUNT",
            joinColumns = @JoinColumn(name = "GROUPNAME",
                    referencedColumnName = "groupName"),
            inverseJoinColumns = @JoinColumn(name = "USERNAME",
                    referencedColumnName = "username"))
    private List<UserAccount> users;

    public UserGroup() {
        this.users = new ArrayList<UserAccount>();
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

    public List<UserAccount> getUsers() {
        return users;
    }

    public void setUsers(List<UserAccount> users) {
        this.users = users;
    }

    public void addUser(UserAccount user) {
        this.users.add(user);
    }

    public void addUsers(List<UserAccount> users) {
        this.users.addAll(users);
    }
}
