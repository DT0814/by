package cn.xawl.by.pojo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "route")
public class Route implements Serializable {
    private String rid;
    private String start;
    private String stop;
    private String tid;

    @Id
    @Column( name = "RID" )
    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    @Basic
    @Column( name = "start" )
    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    @Basic
    @Column( name = "stop" )
    public String getStop() {
        return stop;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

    @Basic
    @Column( name = "TID" )
    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        Route route = (Route) o;

        if ( rid != null ? !rid.equals(route.rid) : route.rid != null ) return false;
        if ( start != null ? !start.equals(route.start) : route.start != null ) return false;
        if ( stop != null ? !stop.equals(route.stop) : route.stop != null ) return false;
        if ( tid != null ? !tid.equals(route.tid) : route.tid != null ) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rid != null ? rid.hashCode() : 0;
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (stop != null ? stop.hashCode() : 0);
        result = 31 * result + (tid != null ? tid.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Route{" +
                "rid='" + rid + '\'' +
                ", start='" + start + '\'' +
                ", stop='" + stop + '\'' +
                ", tid='" + tid + '\'' +
                '}';
    }
}
