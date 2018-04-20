package cn.xawl.by.pojo;

import javax.persistence.*;

@Entity
public class Ciceroni {
    private String cid;
    private String name;
    private String introduce;
    private String tid;
    private Byte status = 0;
    private String statusStr;

    @Transient
    public String getStatusStr() {
        if ( getStatus() == 0 ) {
            this.statusStr = "未激活";
        } else if ( getStatus() == 1 ) {
            this.statusStr = "已停用";
        } else {
            this.statusStr = "正常";
        }
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    @Id
    @Column( name = "CID" )
    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    @Basic
    @Column( name = "name" )
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column( name = "introduce" )
    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
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
    @Column( name = "status" )
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        Ciceroni ciceroni = (Ciceroni) o;

        if ( cid != null ? !cid.equals(ciceroni.cid) : ciceroni.cid != null ) return false;
        if ( name != null ? !name.equals(ciceroni.name) : ciceroni.name != null ) return false;
        if ( introduce != null ? !introduce.equals(ciceroni.introduce) : ciceroni.introduce != null ) return false;
        if ( tid != null ? !tid.equals(ciceroni.tid) : ciceroni.tid != null ) return false;
        if ( status != null ? !status.equals(ciceroni.status) : ciceroni.status != null ) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cid != null ? cid.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (introduce != null ? introduce.hashCode() : 0);
        result = 31 * result + (tid != null ? tid.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Ciceroni{" +
                "cid='" + cid + '\'' +
                ", name='" + name + '\'' +
                ", introduce='" + introduce + '\'' +
                ", tid='" + tid + '\'' +
                ", status=" + status +
                '}';
    }
}
