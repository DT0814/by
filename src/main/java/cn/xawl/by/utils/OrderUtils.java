package cn.xawl.by.utils;


import cn.xawl.by.pojo.Orders;

public class OrderUtils {
    public static String getUpdateString(Orders order) {
        StringBuffer sb = new StringBuffer("update Orders o set ");
        if ( order != null ) {
            if ( order.getStatus() != 0 ) {
                sb.append(",o.status = '" + order.getStatus() + "'");
            }
            if ( order.getfTime() != null ) {
                sb.append(",o.fTime = '" + order.getfTime() + "'");
            }
            sb.append("    where o.oid=" + order.getOid());
            String res = sb.toString();
            res = res.replaceFirst(",", "  ");
            System.out.println(res);
            return res;
        } else {
            return null;
        }


    }
}
