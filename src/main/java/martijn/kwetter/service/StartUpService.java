package martijn.kwetter.service;

import martijn.kwetter.domain.UserAccount;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 * Created by Martijn van der Pol on 02-03-18
 **/

@Startup
@Singleton
public class StartUpService {

    @Inject
    private UserAccountService userAccountService;

    public StartUpService() {

    }

    @PostConstruct
    private void initData() {
        System.out.println("INIT DATA");
        userAccountService.addUserAccount(new UserAccount("MartijnPol", "1234", "martijn.pol@hotmail.com"));
    }
    
}
