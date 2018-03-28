package cn.xawl.by.service;

import cn.xawl.by.dao.UserDao;
import cn.xawl.by.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public List<Users> findAll() {
        List<Users> list = userDao.findAll();
        return list;
    }

    public Users addUser(Users user) {
        user = userDao.addUser(user);
        return user;
    }

    public int updateUser(Users user) {
        int i = userDao.updateUser(user);
        return i;
    }

    public Users delUser(Users user) {
        user = userDao.delUser(user);
        return user;
    }


    public Users findByAccount(String account) {
        Users user = userDao.findByAccount(account);
        return user;
    }
}
