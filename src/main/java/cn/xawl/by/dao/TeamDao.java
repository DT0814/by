package cn.xawl.by.dao;

import cn.xawl.by.pojo.Team;
import cn.xawl.by.utils.TeamUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeamDao {
    @Autowired
    private SessionFactory sessionFactory;
    private Session session = null;

    public List<Team> findAllTeam() {
        session = sessionFactory.openSession();
        Query query = session.createQuery("from  Team ");
        List<Team> list = query.list();
        session.close();
        return list;
    }

    public Team addTeam(Team team) {
        session = sessionFactory.openSession();
        session.save(team);
        session.flush();
        session.close();
        return team;
    }

    public int updateTeam(Team team) {
        session = sessionFactory.openSession();
        String sql = TeamUtils.getUpdateString(team);
        System.out.println(sql);
        Query q = session.createQuery(sql);
        int i = q.executeUpdate();
        session.close();
        return i;
    }

    public void delTeam(Team team) {
        session = sessionFactory.openSession();
        session.delete(team);
        session.flush();
        session.close();
    }

    public Team findByAccount(String account) {
        session = sessionFactory.openSession();
        Query query = session.createQuery("from  Team  t where t.account='" + account + "'");
        Team t = (Team) query.uniqueResult();
        session.close();
        return t;
    }

    public Team findByTid(String tid) {
        session = sessionFactory.openSession();
        Query query = session.createQuery("from  Team  t where t.tid='" + tid + "'");
        Team t = (Team) query.uniqueResult();
        session.close();
        return t;
    }

}
