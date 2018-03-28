package cn.xawl.by.pojo;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table( name = "team" )
public class Team implements Serializable {
    private String tid;
    private String name;
    private String account;
    private String pass;
    private String introduce;

    @Id
    @Column( name = "TID" )
    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
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
    @Column( name = "account" )
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Basic
    @Column( name = "pass" )
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Basic
    @Column( name = "introduce" )
    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        Team team = (Team) o;

        if ( tid != null ? !tid.equals(team.tid) : team.tid != null ) return false;
        if ( name != null ? !name.equals(team.name) : team.name != null ) return false;
        if ( account != null ? !account.equals(team.account) : team.account != null ) return false;
        if ( pass != null ? !pass.equals(team.pass) : team.pass != null ) return false;
        if ( introduce != null ? !introduce.equals(team.introduce) : team.introduce != null ) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tid != null ? tid.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (account != null ? account.hashCode() : 0);
        result = 31 * result + (pass != null ? pass.hashCode() : 0);
        result = 31 * result + (introduce != null ? introduce.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Team{" +
                "tid='" + tid + '\'' +
                ", name='" + name + '\'' +
                ", account='" + account + '\'' +
                ", pass='" + pass + '\'' +
                ", introduce='" + introduce + '\'' +
                '}';
    }
}
