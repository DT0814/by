package cn.xawl.by.pojo;

import cn.xawl.by.utils.DateUtils;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table( name = "tour_group", schema = "by" )
public class TourGroup {
    private String tgid;
    private String cid;
    private String tid;
    private Integer limit;
    private Integer real = 0;
    private Date time = new Date();
    private Float price = 0.0F;
    private Float fprice;
    private byte status;
    private String name;
    private String rid;
    //用于查询传参数的属性
    private String findCondition;
    private String timeStr;
    private int caid;

    @Basic
    @Column( name = "img" )
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    private String img;

    //未完成
    @Transient
    public String getTimeStr() {
        return DateUtils.dateToString(new Date(time.getTime()));
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = "";
    }

    @Transient
    public String getFindCondition() {
        return findCondition;
    }

    public void setFindCondition(String findCondition) {
        this.findCondition = findCondition;
    }

    @Id
    @Column( name = "TGID" )
    public String getTgid() {
        return tgid;
    }

    public void setTgid(String tgid) {
        this.tgid = tgid;
    }

    @Basic
    @Column( name = "RID" )
    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
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
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Basic
    @Column( name = "`price`" )
    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Basic
    @Column( name = "fprice" )
    public Float getFprice() {
        return fprice;
    }

    public void setFprice(Float fprice) {
        this.fprice = fprice;
    }

    @Basic
    @Column( name = "`status`" )
    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    @Basic
    @Column( name = "`name`" )
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column( name = "caid" )
    public int getCaid() {
        return caid;
    }

    public void setCaid(int caid) {
        this.caid = caid;
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
                ", fprice=" + fprice +
                ", status=" + status +
                ", name='" + name + '\'' +
                ", rid='" + rid + '\'' +
                ", findCondition='" + findCondition + '\'' +
                ", timeStr='" + timeStr + '\'' +
                ", caid='" + caid + '\'' +
                '}';
    }
}
