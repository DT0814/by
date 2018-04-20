package cn.xawl.by.dao;

import cn.xawl.by.pojo.Users;
import cn.xawl.by.utils.UserUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDao {
    @Autowired
    private HibernateTemplate template;
    @Autowired
    private SessionFactory sessionFactory;
    Session session = null;

    @Transactional
    public List<Users> findAll() {
        session = sessionFactory.openSession();
        Query query = session.createQuery("from  Users ");
        List<Users> list = query.list();
        session.close();
        return list;
    }

    public Users addUser(Users user) {
        session = sessionFactory.openSession();
        session.save(user);
        session.flush();
        session.close();
        return user;
    }

    public int updateUser(Users user) {
        session = sessionFactory.openSession();
        int i = session.createQuery(UserUtils.getUpdateString(user)).executeUpdate();
        session.flush();
        session.close();
        return i;
    }

    public Users delUser(Users user) {
        session = sessionFactory.openSession();
        session.delete(user);
        session.flush();
        session.close();
        return user;
    }

    public Users findByAccount(String account) {
        session = sessionFactory.openSession();
        Query query = session.createQuery("from Users u where u.account=" + account);
        Users u = (Users) query.uniqueResult();
        session.close();
        return u;
    }

    public Users findByUid(String uid) {
        session = sessionFactory.openSession();
        Query query = session.createQuery("from Users u where u.uid=" + uid);
        Users u = (Users) query.uniqueResult();
        session.close();
        return u;
    }
}
