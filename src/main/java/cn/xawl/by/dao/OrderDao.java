package cn.xawl.by.dao;

import cn.xawl.by.pojo.Orders;
import cn.xawl.by.utils.OrderUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDao {
    @Autowired
    private SessionFactory sessionFactory;
    private Session session = null;

    public Orders createOrder(Orders order) {
        session = sessionFactory.openSession();
        session.save(order);
        session.flush();
        session.close();
        return order;
    }

    public int updateOrder(Orders order) {
        session = sessionFactory.openSession();
        Query q = session.createQuery(OrderUtils.getUpdateString(order));
        int i = q.executeUpdate();
        session.close();
        return i;
    }

    public List findByUid(String uid) {
        session = sessionFactory.openSession();
        Query q = session.createQuery("from Orders where uid='" + uid + "'");
        List list = q.list();
        session.close();
        return list;
    }


    public List findByOid(String oid) {
        session = sessionFactory.openSession();
        Query q = session.createQuery("from Orders o ,TourGroup tg ,Route  r ,Ciceroni  c WHERE tg.tgid=o.tgid AND  r.rid=tg.rid AND c.cid=tg.cid and o.oid='" + oid + "'");
        List list = q.list();
        session.close();
        return list;
    }

    public List findByTid(String tid) {
        session = sessionFactory.openSession();
        Query q = session.createQuery("from Orders where tid='" + tid + "'");
        List list = q.list();
        session.close();
        return list;
    }
}
