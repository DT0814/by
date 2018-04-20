package cn.xawl.by.utils;

import cn.xawl.by.pojo.Users;
import org.junit.Test;

public class UserUtils {
    @Test
    public void fun2() {
        Users u = new Users();
        u.setUid("123123213");
        u.setAccount("32131");
        u.setStatus((byte) 1);
        String s = getUpdateString(u);
        System.out.println(s);
    }

    public static String getUpdateString(Users users) {
        StringBuffer sb = new StringBuffer("update Users u set ");
        if ( users != null ) {
            if ( users.getName() != null ) {
                sb.append(",u.name = '" + users.getName() + "'");
            }
            if ( users.getGd() != null ) {
                sb.append(",u.gd = '" + users.getGd() + "'");
            }
            if ( users.getPassword() != null ) {
                sb.append(",u.password = '" + users.getPassword() + "'");
            }
            if ( users.getAccount() != null ) {
                sb.append(",u.account = '" + users.getAccount() + "'");
            }
            if ( users.getStatus() != 0 ) {
                sb.append(",u.status = " + users.getStatus());
            }
            sb.append("    where u.uid=" + users.getUid());
            String res = sb.toString();
            res = res.replaceFirst(",", "  ");
            return res;
        } else {
            return null;
        }
    }
}
