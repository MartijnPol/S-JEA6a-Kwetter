package service;

import dao.interfaces.HeartDao;
import domain.Heart;
import domain.Kweet;
import domain.UserProfile;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Martijn van der Pol on 13-03-18
 **/

@RunWith(MockitoJUnitRunner.class)
public class HeartServiceTest {

    @Mock
    private HeartDao heartDao;

    @InjectMocks
    private HeartService heartService;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        heartService = new HeartService(heartDao);
    }

    @Test
    public void saveHeartTest() {
        Heart heart = new Heart(new UserProfile(), new Kweet());
        heartDao.save(heart);
        verify(heartDao, Mockito.times(1)).save(heart);
    }

    @Test
    public void findHeartByIdNullTest() {
        Heart heart = new Heart(new UserProfile(), new Kweet());
        heart.setId(1L);

        when(heartService.findById(1L)).thenReturn(heart);
        Heart foundHeart = heartService.findById(null);
        assertNull(foundHeart);
    }

    @Test
    public void findHeartByIdTest() {
        Heart heart = new Heart(new UserProfile(), new Kweet());
        heart.setId(1L);

        when(heartService.findById(1L)).thenReturn(heart);
        Heart foundHeart = heartService.findById(1L);
        assertThat(foundHeart, is(heart));
    }

    @Test
    public void countAllHeartsTest() {
        when(heartService.countAll()).thenReturn(1L);
        Long amountOfHashtags = heartService.countAll();
        assertThat(amountOfHashtags, is(1L));
    }

}
