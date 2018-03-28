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
        return query.list();
    }

    public Route add(Route route) {
        session = sessionFactory.openSession();
        session.save(route);
        session.flush();
        return route;
    }

    public int update(Route route) {
        session = sessionFactory.openSession();
        Query query = session.createQuery(RouteUtlis.getUpdateString(route));
        return query.executeUpdate();
    }

    public void delete(Route route) {
        session = sessionFactory.openSession();
        session.delete(route);
        session.flush();
    }
}
