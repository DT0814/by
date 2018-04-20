package cn.xawl.by.service;

import cn.xawl.by.dao.CarouselDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarouselService {
    @Autowired
    private CarouselDao carouselDao;

    public List findByTgid(String tgid) {
        return carouselDao.findByTgid(tgid);
    }
}
