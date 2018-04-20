package cn.xawl.by.dao;

import cn.xawl.by.pojo.TGResult;
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

    public List<TGResult> findTourGroup() {
        session = sessionFactory.openSession();
        Query query = session.createQuery("from TourGroup tg,Route r,Ciceroni c,Team  t where tg.status != 2 and tg.rid=r.rid and c.cid=tg.cid and t.tid=tg.tid and tg.status=0");
        List list = query.list();
        session.close();
        return list;
    }

    public void add(TourGroup tourGroup) {
        session = sessionFactory.openSession();
        session.save(tourGroup);
        session.flush();
        session.close();
    }

    public int update(TourGroup tourGroup) {
        session = sessionFactory.openSession();
        Query query = session.createQuery(TourGroupUtils.getUpdateString(tourGroup));
        int i = query.executeUpdate();
        session.close();
        return i;
    }

    public List findByName(String name) {
        session = sessionFactory.openSession();
        Query query = session.createQuery("from TourGroup tg,Route r ,Ciceroni c ,Team t where tg.status != 2 and tg.rid=r.rid and  tg.tid=t.tid and tg.cid=c.cid and tg.rid=r.rid and (r.msg like '%" + name + "%' or tg.name like '%" + name + "%' or r.start like '%" + name + "%' or r.stop like '%" + name + "%')");
        List list = query.list();
        session.close();
        return list;
    }

    public List findByPrice(String sInputAmount, String bInputAmount) {
        session = sessionFactory.openSession();
        StringBuffer sb = new StringBuffer("from TourGroup tg,Route r ,Ciceroni c ,Team t where tg.status != 2 and tg.rid=r.rid and tg.tid=t.tid and tg.cid=c.cid   and tg.rid=r.rid");
        if ( sInputAmount != null && !sInputAmount.equals("") ) {
            sb.append("  and tg.fprice>" + sInputAmount);
        }
        if ( bInputAmount != null && !bInputAmount.equals("") ) {
            sb.append("  and tg.fprice<" + bInputAmount);
        }
        System.out.println(sb.toString());
        Query query = session.createQuery(sb.toString());
        List list = query.list();
        session.close();
        return list;
    }

    public List findByTgid(String tgid) {
        session = sessionFactory.openSession();
        Query query = session.createQuery("from TourGroup tg,Route r ,Ciceroni  c,Team t where  tg.tgid=" + tgid + " and r.rid=tg.rid and c.cid=tg.cid and t.tid=tg.tid");
        List list = query.list();
        session.close();
        return list;
    }

    public List findByTid(String tid) {
        session = sessionFactory.openSession();
        Query query = session.createQuery("from TourGroup tg,Route r,Ciceroni c,Team  t,Catalog  cata where tg.status != 2 and tg.rid=r.rid and c.cid=tg.cid and  t.tid=tg.tid and tg.caid=cata.caid and tg.tid='" + tid + "'");
        List list = query.list();
        session.close();
        return list;
    }

    public List findbyCaid(int caid) {
        session = sessionFactory.openSession();
        Query query = session.createQuery("from TourGroup tg,Route r,Ciceroni c,Team  t " +
                "where tg.status != 2 and tg.rid=r.rid and c.cid=tg.cid and t.tid=tg.tid and tg.caid=" + caid + "");
        List list = query.list();
        session.close();
        return list;
    }
}
