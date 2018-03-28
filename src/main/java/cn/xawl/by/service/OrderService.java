package cn.xawl.by.service;

import cn.xawl.by.dao.OrderDao;
import cn.xawl.by.pojo.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderDao orderDao;

    public Orders createOrder(Orders order) {
        return orderDao.createOrder(order);
    }

    public int updateOrder(Orders order) {
        return orderDao.updateOrder(order);
    }
}
