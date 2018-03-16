package service;

import dao.interfaces.HashtagDao;
import domain.Hashtag;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Martijn van der Pol on 13-03-18
 **/

@RunWith(MockitoJUnitRunner.class)
public class HashtagServiceTest {

    Hashtag hashtag = null;

    @Mock
    private HashtagDao hashtagDao;

    private HashtagService hashtagService;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        hashtagService = new HashtagService(hashtagDao);
        hashtag = new Hashtag("#DonaldTrump");
        hashtag.setId(1L);
    }

    @Test
    public void saveHashtagTest() {
        hashtagService.save(hashtag);
        verify(hashtagDao, Mockito.times(1)).save(hashtag);
    }

    @Test
    public void findHashtagByIdTest() {
        when(hashtagService.findById(1L)).thenReturn(hashtag);
        Hashtag foundHashtag = hashtagService.findById(1L);
        assertThat(foundHashtag, is(hashtag));
    }

    @Test
    public void findHashtagBySubjectTest() {
        when(hashtagService.findBySubject("#DonaldTrump")).thenReturn(hashtag);
        Hashtag foundHashtag = hashtagService.findBySubject("#DonaldTrump");
        assertThat(foundHashtag, is(hashtag));
    }

    @Test
    public void countAllHashtagsTest() {
        when(hashtagService.countAll()).thenReturn(1L);
        Long amountOfHashtags = hashtagService.countAll();
        assertThat(amountOfHashtags, is(1L));
    }

}