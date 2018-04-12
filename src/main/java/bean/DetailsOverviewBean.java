package bean;

import domain.Kweet;
import domain.UserAccount;
import service.KweetService;
import service.UserAccountService;
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

    private UserAccount sender;
    private List<Kweet> kweetList;
    private List<Kweet> kweetFilteredList;
    private String accountId;

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
        accountId = paramMap.get("accountId");

        this.sender = userAccountService.findById(new Long(accountId));
        this.kweetList = kweetService.findAllKweetsBySender(sender.getUserProfile());
    }

    public UserAccount getSender() {
        return sender;
    }

    public void setSender(UserAccount sender) {
        this.sender = sender;
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

    public void deleteKweet(Kweet kweet) {
        kweetService.delete(kweet);
        RedirectHelper.redirect("/pages/admin/details.xhtml?accountId=" + accountId);
    }

}
