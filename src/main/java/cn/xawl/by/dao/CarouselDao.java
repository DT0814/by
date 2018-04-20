package cn.xawl.by.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarouselDao {
    @Autowired
    private SessionFactory sessionFactory;
    private Session session = null;

    public List findByTgid(String tgid) {
        session = sessionFactory.openSession();
        Query query = session.createQuery("from Carousel where tgid='" + tgid + "'");
        List list = query.list();
        session.close();
        return list;
    }
}
