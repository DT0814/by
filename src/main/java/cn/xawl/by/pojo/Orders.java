package cn.xawl.by.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Orders implements Serializable {
    private String oid;
    private String uid;
    private Float price;
    private Date cTime;
    private Date fTime;
    private byte status;
    private String tgid;
    private String statusStr;
    private String tid;
    @Id
    @Column( name = "TID" )
    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    @Transient
    public String getStatusStr() {
        //订单状态1创建2付款3完成
        if ( this.getStatus() == 1 ) {
            this.statusStr = "未支付";
        } else if ( this.getStatus() == 2 ) {
            this.statusStr = "已付款";
        } else if ( this.getStatus() == 3 ) {
            this.statusStr = "完成";
        }
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    @Id
    @Column( name = "OID" )
    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    @Basic
    @Column( name = "UID" )
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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
    @Column( name = "cTime" )
    @Temporal( TemporalType.TIMESTAMP )
    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }

    @Basic
    @Column( name = "fTime" )
    @Temporal( TemporalType.TIMESTAMP )
    public Date getfTime() {
        return fTime;
    }

    public void setfTime(Date fTime) {
        this.fTime = fTime;
    }

    @Basic
    @Column( name = "status" )
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

        Orders order = (Orders) o;

        if ( Double.compare(order.price, price) != 0 ) return false;
        if ( status != order.status ) return false;
        if ( oid != null ? !oid.equals(order.oid) : order.oid != null ) return false;
        if ( uid != null ? !uid.equals(order.uid) : order.uid != null ) return false;
        if ( cTime != null ? !cTime.equals(order.cTime) : order.cTime != null ) return false;
        if ( fTime != null ? !fTime.equals(order.fTime) : order.fTime != null ) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = oid != null ? oid.hashCode() : 0;
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (cTime != null ? cTime.hashCode() : 0);
        result = 31 * result + (fTime != null ? fTime.hashCode() : 0);
        result = 31 * result + (int) status;
        return result;
    }

    @Basic
    @Column( name = "TGID" )
    public String getTgid() {
        return tgid;
    }

    public void setTgid(String tgid) {
        this.tgid = tgid;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "oid='" + oid + '\'' +
                ", uid='" + uid + '\'' +
                ", price=" + price +
                ", cTime=" + cTime +
                ", fTime=" + fTime +
                ", status=" + status +
                ", tgid='" + tgid + '\'' +
                '}';
    }
}
