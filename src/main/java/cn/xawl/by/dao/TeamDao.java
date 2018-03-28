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
        return list;
    }

    public Team addTeam(Team team) {
        session = sessionFactory.openSession();
        session.save(team);
        session.flush();
        return team;
    }

    public int updateTeam(Team team) {
        session = sessionFactory.openSession();
        String sql = TeamUtils.getUpdateString(team);
        System.out.println(sql);
        Query q = session.createQuery(sql);
        int i = q.executeUpdate();
        return i;
    }

    public void delTeam(Team team) {
        session = sessionFactory.openSession();
        session.delete(team);
        session.flush();
    }
}
