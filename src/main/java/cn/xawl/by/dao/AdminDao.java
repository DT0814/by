package cn.xawl.by.dao;

import cn.xawl.by.pojo.Admin;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDao {
    @Autowired
    private SessionFactory sessionFactory;
    private Session session = null;

    public Admin findByAccount(String anum) {
        session = sessionFactory.openSession();
        Query query = session.createQuery("FROM  Admin where anum='" + anum + "'");
        Admin a = (Admin) query.uniqueResult();
        session.close();
        return a;
    }
}
