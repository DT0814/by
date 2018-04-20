package cn.xawl.by.dao;

import cn.xawl.by.pojo.Catalog;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CataLogDao {
    @Autowired
    private SessionFactory sessionFactory;
    private Session session = null;

    public List findAll() {
        session = sessionFactory.openSession();
        Query query = session.createQuery("from Catalog ");
        List list = query.list();
        session.close();
        return list;
    }

    public void add(Catalog catalog) {
        session = sessionFactory.openSession();
        session.save(catalog);
        session.flush();
        session.close();
    }

    public int update(Catalog catalog) {
        session = sessionFactory.openSession();
        Query query = session.createQuery("update Catalog set cname='" + catalog.getCname() + "' where caid=" + catalog.getCaid());
        int i = query.executeUpdate();
        session.close();
        return i;
    }

    public void delete(Catalog catalog) {
        session = sessionFactory.openSession();
        session.delete(catalog);
        session.flush();
        session.close();
    }
}
