package cn.xawl.by.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table( name = "tour_group", schema = "by" )
public class TourGroup implements Serializable {
    private String tgid;
    private String cid;
    private String tid;
    private Integer limit;
    private Integer real;
    private Date time;
    private Float price;
    private Float fPrice;
    private byte status;
    private String name;


    @Id
    @Column( name = "TGID" )
    public String getTgid() {
        return tgid;
    }

    public void setTgid(String tgid) {
        this.tgid = tgid;
    }

    @Basic
    @Column( name = "CID" )
    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    @Basic
    @Column( name = "TID" )
    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    @Basic
    @Column( name = "`limit`" )
    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Basic
    @Column( name = "`real`" )
    public Integer getReal() {
        return real;
    }

    public void setReal(Integer real) {
        this.real = real;
    }

    @Basic
    @Column( name = "`time`" )
    @Temporal( TemporalType.TIMESTAMP )
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Basic
    @Column( name = "price" )
    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Basic
    @Column( name = "fPrice" )
    public Float getfPrice() {
        return fPrice;
    }

    public void setfPrice(Float fPrice) {
        this.fPrice = fPrice;
    }

    @Basic
    @Column( name = "`status`" )
    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        TourGroup tourGroup = (TourGroup) o;

        if ( Double.compare(tourGroup.price, price) != 0 ) return false;
        if ( Double.compare(tourGroup.fPrice, fPrice) != 0 ) return false;
        if ( status != tourGroup.status ) return false;
        if ( tgid != null ? !tgid.equals(tourGroup.tgid) : tourGroup.tgid != null ) return false;
        if ( cid != null ? !cid.equals(tourGroup.cid) : tourGroup.cid != null ) return false;
        if ( tid != null ? !tid.equals(tourGroup.tid) : tourGroup.tid != null ) return false;
        if ( limit != null ? !limit.equals(tourGroup.limit) : tourGroup.limit != null ) return false;
        if ( real != null ? !real.equals(tourGroup.real) : tourGroup.real != null ) return false;
        if ( time != null ? !time.equals(tourGroup.time) : tourGroup.time != null ) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = tgid != null ? tgid.hashCode() : 0;
        result = 31 * result + (cid != null ? cid.hashCode() : 0);
        result = 31 * result + (tid != null ? tid.hashCode() : 0);
        result = 31 * result + (limit != null ? limit.hashCode() : 0);
        result = 31 * result + (real != null ? real.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(fPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) status;
        return result;
    }

    @Override
    public String toString() {
        return "TourGroup{" +
                "tgid='" + tgid + '\'' +
                ", cid='" + cid + '\'' +
                ", tid='" + tid + '\'' +
                ", limit=" + limit +
                ", real=" + real +
                ", time=" + time +
                ", price=" + price +
                ", fPrice=" + fPrice +
                ", status=" + status +
                '}';
    }

    @Basic
    @Column( name = "name" )
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
