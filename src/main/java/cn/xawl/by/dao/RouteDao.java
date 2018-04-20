package cn.xawl.by.dao;

import cn.xawl.by.pojo.Route;
import cn.xawl.by.utils.RouteUtlis;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RouteDao {
    @Autowired
    private SessionFactory sessionFactory;
    private Session session = null;

    public List<Route> findAll() {
        session = sessionFactory.openSession();
        Query query = session.createQuery("from Route ");
        List list = query.list();
        session.close();
        return list;
    }

    public Route add(Route route) {
        session = sessionFactory.openSession();
        session.save(route);
        session.flush();
        session.close();
        return route;
    }

    public int update(Route route) {
        session = sessionFactory.openSession();
        Query query = session.createQuery(RouteUtlis.getUpdateString(route));
        int i = query.executeUpdate();
        session.close();
        return i;
    }

    public void delete(Route route) {
        session = sessionFactory.openSession();
        session.delete(route);
        session.flush();
        session.close();
    }

    public List<Route> findByTid(String tid) {
        session = sessionFactory.openSession();
        Query query = session.createQuery("from Route where tid='" + tid + "'");
        List list = query.list();
        session.close();
        return list;
    }
}
