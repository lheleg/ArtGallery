package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.ArtistManager;
import ba.unsa.etf.rpr.business.GalleryManager;
import ba.unsa.etf.rpr.business.PaintingManager;
import ba.unsa.etf.rpr.dao.PaintingDao;
import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Gallery;
import ba.unsa.etf.rpr.domain.Painting;
import ba.unsa.etf.rpr.exceptions.GalleryException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the PaintingManager class.
 */
public class PaintingManagerTest {
    private PaintingManager paintingManager = new PaintingManager();

    @Mock
    private PaintingDao paintingDao;

    @Mock
    private Artist artist;

    @Mock
    private Gallery gallery;

    public Painting pai = new Painting(true,"Mona Lisa",artist,gallery,"");

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void galleryConstructorTest() {
        assertEquals(true, pai.getAvailable());
        assertEquals("Mona Lisa", pai.getTitle());
        assertEquals("", pai.getImage());
    }

    @Test
    void fetchPaintings() throws GalleryException {
        Painting painting = new Painting(true,"slikca",new ArtistManager().fetchArtists().get(0),new GalleryManager().fetchGalleries().get(0),"");
        Painting addedPainting = paintingManager.add(painting);
        List<Painting> allPaintings = paintingManager.fetchPaintings();
        boolean isAdded = allPaintings.contains(addedPainting);
        Assertions.assertTrue(isAdded);
        paintingManager.delete(addedPainting.getId());
    }

    @Test
    void updateTest() throws Exception {
        pai.setTitle("Lejla's painting");
        paintingDao.update(pai);
        verify(paintingDao).update(pai);
    }
}
