package cn.xawl.by.utils;

import cn.xawl.by.pojo.TourGroup;

public class TourGroupUtils {


    public static String getUpdateString(TourGroup tourGroup) {
        StringBuffer sb = new StringBuffer("update TourGroup t set ");
        if ( tourGroup != null ) {
            if ( tourGroup.getStatus() != 0 ) {
                sb.append(" ,t.status = '" + tourGroup.getStatus() + "'");
            }
            if ( tourGroup.getName() != null && !tourGroup.getName().equals("") ) {
                sb.append(" ,t.name = '" + tourGroup.getName() + "'");
            }
            if ( tourGroup.getTime() != null ) {
                sb.append(" ,t.time = '" + DateUtils.getDateString(tourGroup.getTime()) + "'");
            }
            if ( tourGroup.getFprice() != null ) {
                sb.append(" ,t.fprice = '" + tourGroup.getFprice() + "'");
            }
            if ( tourGroup.getPrice() != null ) {
                sb.append(" ,t.price = '" + tourGroup.getPrice() + "'");
            }
            if ( tourGroup.getLimit() != null ) {
                sb.append(" ,t.limit = '" + tourGroup.getLimit() + "'");
            }
            if ( tourGroup.getReal() != null ) {
                sb.append(" ,t.real = '" + tourGroup.getReal() + "'");
            }
            if ( tourGroup.getTid() != null && !tourGroup.getTid().equals("") ) {
                sb.append(" ,t.tid = '" + tourGroup.getTid() + "'");
            }
            if ( tourGroup.getCid() != null && !tourGroup.getCid().equals("") ) {
                sb.append(" ,t.cid = '" + tourGroup.getCid() + "'");
            }
            if ( tourGroup.getRid() != null && !tourGroup.getRid().equals("") ) {
                sb.append(" ,t.rid = '" + tourGroup.getRid() + "'");
            }
            sb.append("  where t.tgid= '" + tourGroup.getTgid() + "'");
            String res = sb.toString();
            res = res.replaceFirst(",", "");
            System.out.println(res);
            return res;
        } else {
            return null;
        }
    }
}
