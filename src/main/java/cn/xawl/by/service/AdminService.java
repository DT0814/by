package cn.xawl.by.service;

import cn.xawl.by.dao.AdminDao;
import cn.xawl.by.pojo.Admin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminDao adminDao;

    public Admin findByAccount(String anum) {
        return adminDao.findByAccount(anum);
    }
}
