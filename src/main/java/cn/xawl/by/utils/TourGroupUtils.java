package cn.xawl.by.utils;

import cn.xawl.by.pojo.TourGroup;

public class TourGroupUtils {


    public static String getUpdateString(TourGroup tourGroup) {
        StringBuffer sb = new StringBuffer("update TourGroup t set ");
        if ( tourGroup != null ) {
            if ( tourGroup.getStatus() != 0 ) {
                sb.append(" ,t.status = '" + tourGroup.getStatus() + "'");
            }
            if ( tourGroup.getName() != null ) {
                sb.append(" ,t.name = '" + tourGroup.getName() + "'");
            }
            if ( tourGroup.getTime() != null ) {
                sb.append(" ,t.time = '" + tourGroup.getTime() + "'");
            }
            if ( tourGroup.getfPrice() != null ) {
                sb.append(" ,t.fPrice = '" + tourGroup.getfPrice() + "'");
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
            if ( tourGroup.getTid() != null ) {
                sb.append(" ,t.tid = '" + tourGroup.getTid() + "'");
            }

            sb.append("  where t.tgid=" + tourGroup.getTgid());
            String res = sb.toString();
            res = res.replaceFirst(",", "");
            System.out.println(res);
            return res;
        } else {
            return null;
        }
    }
}
