package cn.xawl.by.pojo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table( name = "route" )
public class Route implements Serializable {
    private String rid;
    private String start;
    private String stop;
    private String tid;
    private String msg;


    @Id
    @Column( name = "RID" )
    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    @Basic
    @Column( name = "msg" )
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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
    public String toString() {
        return "Route{" +
                "rid='" + rid + '\'' +
                ", start='" + start + '\'' +
                ", stop='" + stop + '\'' +
                ", tid='" + tid + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
