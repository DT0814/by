package cn.xawl.by.service;

import cn.xawl.by.dao.TourGroupDao;
import cn.xawl.by.pojo.TGResult;
import cn.xawl.by.pojo.TourGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourGroupService {
    @Autowired
    private TourGroupDao tourGroupDao;

    public List<TGResult> findTourGroup() {
        return tourGroupDao.findTourGroup();
    }

    public void add(TourGroup tourGroup) {
        tourGroupDao.add(tourGroup);
    }

    public int update(TourGroup tourGroup) {
        return tourGroupDao.update(tourGroup);
    }

    public List findByName(String name) {
        return tourGroupDao.findByName(name);
    }

    public List findByPrice(String sInputAmount, String bInputAmount) {
        return tourGroupDao.findByPrice(sInputAmount, bInputAmount);
    }

    public List findByTgid(String tgid) {
        return tourGroupDao.findByTgid(tgid);
    }

    public List findByTid(String tid) {
        return tourGroupDao.findByTid(tid);
    }

    public List findbyCaid(int caid) {
        return tourGroupDao.findbyCaid(caid);
    }
}
