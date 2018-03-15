package service;

import dao.interfaces.UserAccountDao;
import domain.UserAccount;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Martijn van der Pol on 15-03-18
 **/
@RunWith(MockitoJUnitRunner.class)
public class UserAccountServiceTest {

    UserAccount userAccount = null;
    private UserAccountService userAccountService;
    @Mock
    private UserAccountDao userAccountDao;

    @Before
    public void setUp() {
        userAccountService = new UserAccountService(userAccountDao);
        userAccount = new UserAccount("test", "1234", "test@test.com");
    }

    @Test
    public void saveUserAccountTest() {
        userAccountService.save(userAccount);
        verify(userAccountDao, Mockito.times(1)).save(userAccount);
    }

    @Test
    public void findAccountSuccessful() {
        when(userAccountService.findByUsername("test")).thenReturn(userAccount);
        UserAccount found = userAccountService.findByUsername(userAccount.getUsername());
        assertThat(found, is(userAccount));
    }
}