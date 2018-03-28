package cn.xawl.by.dao;

import cn.xawl.by.pojo.Orders;
import cn.xawl.by.utils.OrderUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDao {
    @Autowired
    private SessionFactory sessionFactory;
    private Session session = null;

    public Orders createOrder(Orders order) {
        session = sessionFactory.openSession();
        session.save(order);
        session.flush();
        return order;
    }

    public int updateOrder(Orders order) {
        session = sessionFactory.openSession();
        Query q = session.createQuery(OrderUtils.getUpdateString(order));
        int i = q.executeUpdate();
        return i;
    }
}
