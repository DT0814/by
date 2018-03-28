package cn.xawl.by.utils;

import cn.xawl.by.pojo.Team;
import org.junit.Test;

public class TeamUtils {

    @Test
    public void fun() {
        Team team = new Team();
        team.setTid("1233");
        team.setAccount("wedasd");
        team.setIntroduce("3213fda fdas ");
        team.setName("123123");
        System.out.println(getUpdateString(team));
    }

    public static String getUpdateString(Team team) {
        StringBuffer sb = new StringBuffer("update Team t set ");
        if ( team != null ) {
            if ( team.getPass() != null ) {
                sb.append(" ,t.pass = '" + team.getPass() + "'");
            }
            if ( team.getAccount() != null ) {
                sb.append(" ,t.account = '" + team.getAccount() + "'");
            }
            if ( team.getName() != null ) {
                sb.append(" ,t.name = '" + team.getName() + "'");
            }
            if ( team.getIntroduce() != null ) {
                sb.append(" ,t.introduce = '" + team.getIntroduce() + "'");
            }
            sb.append("  where t.tid=" + team.getTid());
            String res = sb.toString();
            res = res.replaceFirst(",", "");
            return res;
        } else {
            return null;
        }

    }
}
