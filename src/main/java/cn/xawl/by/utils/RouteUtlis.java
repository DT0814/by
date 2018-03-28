package cn.xawl.by.utils;


import cn.xawl.by.pojo.Route;

public class RouteUtlis {
    public static String getUpdateString(Route route) {
        StringBuffer sb = new StringBuffer("update Route r set ");
        if ( route != null ) {
            if ( route.getTid() != null ) {
                sb.append(" ,r.tid = '" + route.getTid() + "'");
            }
            if ( route.getStart() != null ) {
                sb.append(" ,r.start = '" + route.getStart() + "'");
            }
            if ( route.getStop() != null ) {
                sb.append(" ,r.stop = '" + route.getStop() + "'");
            }
            sb.append("  where r.rid=" + route.getRid());
            String res = sb.toString();
            res = res.replaceFirst(",", "");
            return res;
        } else {
            return null;
        }
    }
}
