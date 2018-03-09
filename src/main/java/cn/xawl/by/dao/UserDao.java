package cn.xawl.by.dao;

import cn.xawl.by.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserDao {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    public List<User> findAll() {
        List<User> list = (List<User>) hibernateTemplate.
                find("from User ");
        return list;
    }
}
