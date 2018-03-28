package cn.xawl.by.service;

import cn.xawl.by.dao.RouteDao;
import cn.xawl.by.pojo.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {
    @Autowired
    private RouteDao routeDao;

    public List<Route> findAll() {
        return routeDao.findAll();
    }

    public Route add(Route route) {
        return routeDao.add(route);
    }

    public int update(Route route) {
        return routeDao.update(route);
    }

    public void delete(Route route) {
        routeDao.delete(route);
    }
}
