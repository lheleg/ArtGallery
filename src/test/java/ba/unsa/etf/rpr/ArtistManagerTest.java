package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.ArtistManager;
import ba.unsa.etf.rpr.dao.ArtistDao;
import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.exceptions.GalleryException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

public class ArtistManagerTest {
    private ArtistManager artistManager = new ArtistManager();

    @Mock
    private ArtistDao artistDao;

    public Artist artist = new Artist("Pablo", "Picasso", "Cubism","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSGEKd02e-UADD5nLoYdT0_lKmV_Cl24s2HWdQcg3CoPy6KAS-S");

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void artistConstructorTest() {
        assertEquals("Pablo", artist.getFirstName());
        assertEquals("Picasso", artist.getLastName());
        assertEquals("Cubism", artist.getStyle());
        assertEquals("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSGEKd02e-UADD5nLoYdT0_lKmV_Cl24s2HWdQcg3CoPy6KAS-S", artist.getImage());
    }

    @Test
    void fetchArtists() throws GalleryException {
        Artist addedArt = artistManager.add(artist);
        List<Artist> allArtists = artistManager.fetchArtists();
        boolean isAdded = allArtists.contains(addedArt);
        Assertions.assertTrue(isAdded);
        artistManager.delete(addedArt.getId());
    }

    @Test
    void updateTest() throws Exception {
        artist.setFirstName("Lejla");
        artistDao.update(artist);
        verify(artistDao).update(artist);
    }
}
