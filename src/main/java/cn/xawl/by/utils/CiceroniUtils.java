package cn.xawl.by.utils;

import cn.xawl.by.pojo.Ciceroni;

public class CiceroniUtils {
    public static String getUpdateString(Ciceroni ciceroni) {
        StringBuffer sb = new StringBuffer("update Ciceroni c set ");
        if ( ciceroni != null ) {
            if ( ciceroni.getName() != null ) {
                sb.append(" ,c.name = '" + ciceroni.getName() + "'");
            }
            if ( ciceroni.getStatus() != null && ciceroni.getStatus() != 0 ) {
                sb.append(" ,c.status = '" + ciceroni.getStatus() + "'");
            }
            if ( ciceroni.getIntroduce() != null ) {
                sb.append(" ,c.introduce = '" + ciceroni.getIntroduce() + "'");
            }
            sb.append("  where c.cid=" + ciceroni.getCid());
            String res = sb.toString();
            res = res.replaceFirst(",", "");
            return res;
        } else {
            return null;
        }
    }
}
