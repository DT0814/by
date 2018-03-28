package cn.xawl.by.pojo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;


@Entity
public class Users implements Serializable {
    private String uid;
    private String account;
    private String password;
    private byte status;

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

        Users user = (Users) o;

        if ( status != user.status ) return false;
        if ( uid != null ? !uid.equals(user.uid) : user.uid != null ) return false;
        if ( account != null ? !account.equals(user.account) : user.account != null ) return false;
        if ( password != null ? !password.equals(user.password) : user.password != null ) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uid != null ? uid.hashCode() : 0;
        result = 31 * result + (account != null ? account.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (int) status;
        return result;
    }

    @Override
    public String toString() {
        return "Users{" +
                "uid='" + uid + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                '}';
    }
}
