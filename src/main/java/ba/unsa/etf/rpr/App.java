package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.*;
import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Gallery;
import ba.unsa.etf.rpr.domain.Painting;


import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        GalleryDao dao = new GalleryDaoSQLImpl();

        Gallery gallery = new Gallery();
        gallery.setId(1);
          gallery.setName("The Grand Gallery of the Louvre");
      //  dao.delete(3);
        List<Gallery> g = dao.getAll();
        for(Gallery ga : g) System.out.println(ga);

       ArtistDao dao2 = new ArtistDaoSQLImpl();

        Artist artist = new Artist();
        artist.setId(2);
        artist.setFirstName("Leonardo");
        artist.setLastName("da Vinci");
        artist.setStyle("High Renaissance");
       // dao2.add(artist);
        List<Artist> a = dao2.getAll();
        for(Artist ar : a) System.out.println(ar);

        PaintingDao dao1 = new PaintingDaoSQLImpl();

        Painting painting = new Painting();
        painting.setId(1);
        painting.setAvailable(true);
        painting.setTitle("Mona Lisa");
        painting.setArtist(dao2.getById(2));
        painting.setGallery(dao.getById(2));
        dao1.add(painting);
        List<Painting> p = dao1.getAll();
        for(Painting pa : p) System.out.println(pa);
    }
}
