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
        return list;
    }

    public Users addUser(Users user) {
        session = sessionFactory.openSession();
        session.save(user);
        session.flush();
        return user;
    }

    public int updateUser(Users user) {
        session = sessionFactory.openSession();
        Query q = session.createQuery(UserUtils.getUpdateString(user));
        int i = q.executeUpdate();
        return i;
    }

    public Users delUser(Users user) {
        session = sessionFactory.openSession();
        session.delete(user);
        return user;
    }

    public Users findByAccount(String account) {
        Users u = (Users) template.find("from Users u where u.account=" + account);
        return u;
    }
}
