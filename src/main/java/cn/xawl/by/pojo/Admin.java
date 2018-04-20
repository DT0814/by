package cn.xawl.by.pojo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Admin {
    private int aid;
    private String apss;
    private String anum;

    @Id
    @Column( name = "aid" )
    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    @Basic
    @Column( name = "apss" )
    public String getApss() {
        return apss;
    }

    public void setApss(String apss) {
        this.apss = apss;
    }

    @Basic
    @Column( name = "anum" )
    public String getAnum() {
        return anum;
    }

    public void setAnum(String anum) {
        this.anum = anum;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        Admin admin = (Admin) o;

        if ( aid != admin.aid ) return false;
        if ( apss != null ? !apss.equals(admin.apss) : admin.apss != null ) return false;
        if ( anum != null ? !anum.equals(admin.anum) : admin.anum != null ) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = aid;
        result = 31 * result + (apss != null ? apss.hashCode() : 0);
        result = 31 * result + (anum != null ? anum.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "aid=" + aid +
                ", apss='" + apss + '\'' +
                ", anum='" + anum + '\'' +
                '}';
    }
}
