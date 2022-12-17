package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.ArtistDao;
import ba.unsa.etf.rpr.dao.ArtistDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Artist;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        ArtistDao dao = new ArtistDaoSQLImpl();

   //     Artist artist = new Artist();
   //1     artist.setId(1);
   //       artist.setFirstName("lele");
   //     artist.setLastName("Picasso");
   //    artist.setStyle("neoclassical");
  //  dao.add(artist);
        List<Artist> a = dao.getAll();
        for(Artist ar : a) System.out.println(ar);

    }
}
