package ba.unsa.etf.rpr.dao;

/**
 * Factory method for singleton implementation of DAOs
 *
 * @author Lejla Heleg
 */
public class DaoFactory {

    private static final ArtistDao artistDao = new ArtistDaoSQLImpl();
    private static final PaintingDao paintingDao = new PaintingDaoSQLImpl();
    private static final GalleryDao galleryDao = new GalleryDaoSQLImpl();

    private DaoFactory(){
    }

    public static ArtistDao artistDao(){
        return artistDao;
    }

    public static PaintingDao paintingDao(){
        return paintingDao;
    }

    public static GalleryDao galleryDao(){
        return galleryDao;
    }

}

