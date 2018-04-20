package cn.xawl.by.service;

import cn.xawl.by.dao.CataLogDao;
import cn.xawl.by.pojo.Catalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CataLogService {
    @Autowired
    private CataLogDao cataLogDao;

    public List findAll() {
        return cataLogDao.findAll();
    }


    public void add(Catalog catalog) {
        cataLogDao.add(catalog);
    }

    public int update(Catalog catalog) {
        return cataLogDao.update(catalog);
    }

    public void delete(Catalog catalog) {
        cataLogDao.delete(catalog);
    }
}
