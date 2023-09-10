package ba.unsa.etf.rpr.domain;

import java.util.Date;

/**
 * bean for Wish
 * @author Lejla Heleg
 */
public class Wish implements Idable{
    private int id;
    private Date savedDate;
    private Date unsavedDate = null;
    private Painting painting;
    private User user;

    /**
     * Makes instance of Wish.
     *
     * @param savedDate  the savedDate
     * @param unsavedDate  the unsavedDate
     * @param painting the painting
     * @param user  the user
     */
    public Wish(Date savedDate, Date unsavedDate, Painting painting, User user) {
        this.savedDate = savedDate;
        this.unsavedDate = unsavedDate;
        this.painting = painting;
        this.user = user;
    }

    /**
     * Makes instance of Wish.
     */
    public Wish() {
    }
    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return this.id;
    }

    public Date getSavedDate() {
        return savedDate;
    }

    public void setSavedDate(Date savedDate) {
        this.savedDate = savedDate;
    }

    public Date getUnsavedDate() {
        return unsavedDate;
    }

    public void setUnsavedDate(Date unsavedDate) {
        this.unsavedDate = unsavedDate;
    }

    public Painting getPainting() {
        return painting;
    }

    public void setPainting(Painting painting) {
        this.painting = painting;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wish wish = (Wish) o;
        return id == wish.id;
    }

    @Override
    public String toString() {
        return "Wish{" +
                "id=" + id +
                ", savedDate=" + savedDate +
                ", unsavedDATE=" + unsavedDate +
                ", painting=" + painting +
                ", user=" + user +
                '}';
    }
}
