package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Painting;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.domain.Wish;
import ba.unsa.etf.rpr.exceptions.GalleryException;

import java.util.Date;
import java.util.List;

public interface WishDao extends Dao<Wish>{
    public List<Wish> getUserSavedPaintings(User user) throws GalleryException;

}
