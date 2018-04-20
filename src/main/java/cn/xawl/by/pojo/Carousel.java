package cn.xawl.by.pojo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Carousel {
    private String cid;
    private String imageUrl;
    private String title;
    private String tgid;

    @Id
    @Column( name = "cid" )
    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    @Basic
    @Column( name = "imageUrl" )
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Basic
    @Column( name = "title" )
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column( name = "tgid" )
    public String getTgid() {
        return tgid;
    }

    public void setTgid(String tgid) {
        this.tgid = tgid;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        Carousel carousel = (Carousel) o;

        if ( cid != null ? !cid.equals(carousel.cid) : carousel.cid != null ) return false;
        if ( imageUrl != null ? !imageUrl.equals(carousel.imageUrl) : carousel.imageUrl != null ) return false;
        if ( title != null ? !title.equals(carousel.title) : carousel.title != null ) return false;
        if ( tgid != null ? !tgid.equals(carousel.tgid) : carousel.tgid != null ) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cid != null ? cid.hashCode() : 0;
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (tgid != null ? tgid.hashCode() : 0);
        return result;
    }
}
