package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * bean of galleries
 * @author Lejla Heleg
 */
public class Gallery implements Idable{
    private int id;
    private String name;
    private String url;
    private String image;

    public Gallery(){}

    public Gallery(String name, String url, User user, String image) {
        this.name = name;
        this.url = url;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gallery that = (Gallery) o;
        return id == that.id;
    }

    @Override
    public String toString() {
        return "Gallery{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
