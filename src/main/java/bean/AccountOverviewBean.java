package bean;

import domain.UserAccount;
import service.UserAccountService;
import utils.RedirectHelper;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Martijn van der Pol on 06-04-18
 **/
@Named("AccountOverviewBean")
@ViewScoped
public class AccountOverviewBean implements Serializable {

    @Inject
    private UserAccountService userAccountService;

    private List<UserAccount> userAccountList;
    private List<UserAccount> userAccountFilteredList;

    @PostConstruct
    public void init() {
        this.userAccountList = this.userAccountService.getAll();
    }

    public List<UserAccount> getUserAccountList() {
        return userAccountList;
    }

    public void setUserAccountList(List<UserAccount> userAccountList) {
        this.userAccountList = userAccountList;
    }

    public List<UserAccount> getUserAccountFilteredList() {
        return userAccountFilteredList;
    }

    public void setUserAccountFilteredList(List<UserAccount> userAccountFilteredList) {
        this.userAccountFilteredList = userAccountFilteredList;
    }

    public void deleteAccount(UserAccount account) {
        userAccountService.deleteById(account.getId());
    }

    public void loadDetails(UserAccount account) {
        RedirectHelper.redirect("/pages/admin/details.xhtml?accountId=" + account.getId());
    }

}