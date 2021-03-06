package bean;

import domain.Kweet;
import domain.UserAccount;
import domain.UserGroup;
import service.KweetService;
import service.UserAccountService;
import service.UserGroupService;
import utils.RedirectHelper;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Martijn van der Pol on 12-04-18
 **/
@Named("DetailsOverviewBean")
@ViewScoped
public class DetailsOverviewBean implements Serializable {

    @Inject
    private KweetService kweetService;

    @Inject
    private UserAccountService userAccountService;

    @Inject
    private UserGroupService userGroupService;

    private UserAccount user;
    private List<Kweet> kweetList;
    private List<Kweet> kweetFilteredList;
    private String accountId;

    private List<UserGroup> userGroups;
    private String userGroup;

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
        accountId = paramMap.get("accountId");

        this.user = userAccountService.findById(new Long(accountId));
        this.kweetList = kweetService.findAllKweetsBySender(user.getId());
        this.userGroups = userGroupService.getAll();
    }

    public UserAccount getUser() {
        return user;
    }

    public void setUser(UserAccount user) {
        this.user = user;
    }

    public List<Kweet> getKweetList() {
        return kweetList;
    }

    public void setKweetList(List<Kweet> kweetList) {
        this.kweetList = kweetList;
    }

    public List<Kweet> getKweetFilteredList() {
        return kweetFilteredList;
    }

    public void setKweetFilteredList(List<Kweet> kweetFilteredList) {
        this.kweetFilteredList = kweetFilteredList;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public void deleteKweet(Kweet kweet) {
        kweetService.delete(kweet);
        RedirectHelper.redirect("/pages/admin/details.xhtml?accountId=" + accountId);
    }

    public void updateUserAccount() {

        for (UserGroup userGroup : userGroups) {
            if (userGroup.getAccounts().contains(this.user)) {
                userGroup.getAccounts().remove(this.user);
                this.userGroupService.update(userGroup);
            }
        }

        UserGroup foundUserGroup = this.userGroupService.findByName(this.userGroup);
        if (!foundUserGroup.getAccounts().contains(this.user)) {
            foundUserGroup.addUser(this.user);
            this.userGroupService.update(foundUserGroup);
        }

        this.userAccountService.update(this.user);

        RedirectHelper.redirect("/pages/admin/details.xhtml?accountId=" + accountId);
    }

}
