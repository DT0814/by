package cn.xawl.by.service;

import cn.xawl.by.dao.TourGroupDao;
import cn.xawl.by.pojo.TourGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourGroupService {
    @Autowired
    private TourGroupDao tourGroupDao;

    public List<TourGroup> findTourGroup() {
       return tourGroupDao.findTourGroup();
    }

    public void add(TourGroup tourGroup) {
        tourGroupDao.add(tourGroup);
    }

    public int update(TourGroup tourGroup) {
        return tourGroupDao.update(tourGroup);
    }
}
