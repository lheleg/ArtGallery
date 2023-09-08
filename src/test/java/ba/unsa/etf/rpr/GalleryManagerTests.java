package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.GalleryManager;
import ba.unsa.etf.rpr.dao.GalleryDao;
import ba.unsa.etf.rpr.domain.Gallery;
import ba.unsa.etf.rpr.exceptions.GalleryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GalleryManagerTests {
    private GalleryManager galleryManager = new GalleryManager();
    @Mock
    private GalleryDao galleryDao;
    public Gallery gall = new Gallery("Uffizi Gallery", "https://www.uffizi.it/en/the-uffizi","https://t2.gstatic.com/licensed-image?q=tbn:ANd9GcTMdL0DVk-vRAAReX6d5c78hBUvnKqC5cXJ6EkhjGiJY7eZNBHfAZsyDyD5yb9syBha");

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void galleryConstructorTest() {
        assertEquals("Uffizi Gallery", gall.getName());
        assertEquals("https://t2.gstatic.com/licensed-image?q=tbn:ANd9GcTMdL0DVk-vRAAReX6d5c78hBUvnKqC5cXJ6EkhjGiJY7eZNBHfAZsyDyD5yb9syBha", gall.getImage());
        assertEquals("https://www.uffizi.it/en/the-uffizi", gall.getUrl());
    }

    @Test
    void add() throws GalleryException {
        Gallery addedGall = galleryManager.add(gall);
        List<Gallery> allGalleries = galleryManager.fetchGalleries();
        boolean isAdded = allGalleries.contains(addedGall);
        Assertions.assertTrue(isAdded);
        galleryManager.delete(addedGall.getId());
    }

    @Test
    void updateTest() throws Exception {
        gall.setName("Lejla's gallery");
        galleryDao.update(gall);
        verify(galleryDao).update(gall);

        galleryManager.delete(gall.getId());
    }




}
