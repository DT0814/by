package cn.xawl.by.dao;

import cn.xawl.by.pojo.TourGroup;
import cn.xawl.by.utils.TourGroupUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TourGroupDao {
    @Autowired
    private SessionFactory sessionFactory;
    private Session session = null;

    public List<TourGroup> findTourGroup() {
        session = sessionFactory.openSession();
        Query query = session.createQuery("from TourGroup where TourGroup.status != 2");
        return query.list();
    }

    public void add(TourGroup tourGroup) {
        session = sessionFactory.openSession();
        session.save(tourGroup);
        session.flush();
    }

    public int update(TourGroup tourGroup) {
        session = sessionFactory.openSession();
        Query query = session.createQuery(TourGroupUtils.getUpdateString(tourGroup));
        int i = query.executeUpdate();
        return i;
    }
}
