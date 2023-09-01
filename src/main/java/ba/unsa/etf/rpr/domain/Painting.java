package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * bean for painting
 * @author Lejla Heleg
 */
public class Painting implements Idable{
    private int id;
    private boolean available;
    private String title;
    private Artist artist;
    private Gallery gallery;
    private String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Gallery getGallery() {
        return gallery;
    }

    public void setGallery(Gallery gallery) {
        this.gallery = gallery;
    }
    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }

    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Painting{" +
                "id=" + id +
                ", availability='" + available + '\'' +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", gallery='" + gallery + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Painting that = (Painting) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, available, artist, gallery);
    }

}
