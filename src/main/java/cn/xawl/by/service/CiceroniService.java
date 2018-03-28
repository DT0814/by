package cn.xawl.by.service;

import cn.xawl.by.dao.CiceroniDao;
import cn.xawl.by.pojo.Ciceroni;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CiceroniService {
    @Autowired
    private CiceroniDao ciceroniDao;

    public List<Ciceroni> findAll() {
        return ciceroniDao.findAll();
    }

    public Ciceroni add(Ciceroni ciceroni) {
        return ciceroniDao.add(ciceroni);
    }

    public int update(Ciceroni ciceroni) {
        return ciceroniDao.update(ciceroni);
    }

    public void delete(Ciceroni ciceroni) {
        ciceroniDao.delete(ciceroni);
    }
}
