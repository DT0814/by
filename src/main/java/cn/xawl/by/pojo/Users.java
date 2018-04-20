package cn.xawl.by.pojo;

import javax.persistence.*;

@Entity
public class Users {
    private String uid;
    private String account;
    private transient String password;
    private byte status;
    private String name;
    private String gd;
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

    @Id
    @Column( name = "UID" )
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Basic
    @Column( name = "account" )
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Basic
    @Column( name = "password" )
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    @Column( name = "gd" )
    public String getGd() {
        return gd;
    }

    public void setGd(String gd) {
        this.gd = gd;
    }

    @Override
    public String toString() {
        return "Users{" +
                "uid='" + uid + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", name='" + name + '\'' +
                ", gd='" + gd + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        Users users = (Users) o;

        if ( status != users.status ) return false;
        if ( uid != null ? !uid.equals(users.uid) : users.uid != null ) return false;
        if ( account != null ? !account.equals(users.account) : users.account != null ) return false;
        if ( password != null ? !password.equals(users.password) : users.password != null ) return false;
        if ( name != null ? !name.equals(users.name) : users.name != null ) return false;
        if ( gd != null ? !gd.equals(users.gd) : users.gd != null ) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uid != null ? uid.hashCode() : 0;
        result = 31 * result + (account != null ? account.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (int) status;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (gd != null ? gd.hashCode() : 0);
        return result;
    }
}
