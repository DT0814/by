package cn.xawl.by.pojo;

import javax.persistence.*;

@Entity
public class Catalog {
    private Integer caid;
    private String cname;

    @Id
    @Column( name = "caid" )
    @GeneratedValue( strategy = GenerationType.AUTO )
    public Integer getCaid() {
        return caid;
    }

    public void setCaid(Integer cid) {
        this.caid = cid;
    }

    @Basic
    @Column( name = "cname" )
    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        Catalog catalog = (Catalog) o;

        if ( caid != catalog.caid ) return false;
        if ( cname != null ? !cname.equals(catalog.cname) : catalog.cname != null ) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = caid;
        result = 31 * result + (cname != null ? cname.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "caid=" + caid +
                ", cname='" + cname + '\'' +
                '}';
    }
}
