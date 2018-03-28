package cn.xawl.by.dao;

import cn.xawl.by.pojo.Ciceroni;
import cn.xawl.by.utils.CiceroniUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CiceroniDao {
    @Autowired
    private SessionFactory sessionFactory;
    private Session session = null;

    public List<Ciceroni> findAll() {
        session = sessionFactory.openSession();
        Query query = session.createQuery("from Ciceroni ");
        return query.list();
    }

    public Ciceroni add(Ciceroni ciceroni) {
        session = sessionFactory.openSession();
        session.save(ciceroni);
        session.flush();
        return ciceroni;
    }

    public int update(Ciceroni ciceroni) {
        session = sessionFactory.openSession();
        Query query = session.createQuery(CiceroniUtils.getUpdateString(ciceroni));
        int i = query.executeUpdate();
        return i;
    }

    public void delete(Ciceroni ciceroni) {
        session = sessionFactory.openSession();
        session.delete(ciceroni);
        session.flush();
    }
}
