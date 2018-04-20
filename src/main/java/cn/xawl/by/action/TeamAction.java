package cn.xawl.by.action;

import cn.xawl.by.pojo.Team;
import cn.xawl.by.service.TeamService;
import cn.xawl.by.utils.Result;
import cn.xawl.by.utils.Utils;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TeamAction extends ActionSupport {
    @Autowired
    private TeamService teamService;

    public Team getTeam() {
        return team;
    }

    private Team team = new Team();

    private Result result;

    public Result getResult() {
        return result;
    }

    public String login() {
        try {
            System.out.println(team);
            Team t = teamService.findByAccount(team.getAccount());
            if ( t == null ) {
                result = Result.err(300, "账号不存在");
            } else if ( team.getPass().equals(t.getPass()) ) {
                result = Result.success(t);
            } else {
                result = Result.err(300, "密码错误");
            }
            return ActionSupport.SUCCESS;
        } catch ( Exception e ) {
            e.printStackTrace();
            result = Result.err(400, "Login出错");
            return ActionSupport.ERROR;
        }
    }

    public String findAllTeam() {
        try {
            List<Team> teams = teamService.findAllTeam();
            result = Result.success(teams);
            return ActionSupport.SUCCESS;
        } catch ( Exception e ) {
            e.printStackTrace();
            result = Result.err(400, "查询失败");
            return ActionSupport.ERROR;
        }
    }

    /**
     * 注册
     *
     * @return
     */
    public String register() {
        try {
            System.out.println(team);
            team.setPass(team.getPass().split(",")[0]);
            team.setTid(Utils.CreateID());
            if ( teamService.findByAccount(team.getAccount()) != null ) {
                result = Result.err(300, "账号已经存在");
                return ActionSupport.SUCCESS;
            }

            team = teamService.addTeam(team);
            result = Result.success(team);
            return ActionSupport.SUCCESS;
        } catch ( Exception e ) {
            e.printStackTrace();
            result = Result.err(400, "添加失败");
            return ActionSupport.ERROR;
        }
    }

    public String addTeam() {
        try {
            System.out.println(team);
            team.setTid(Utils.CreateID());
            team = teamService.addTeam(team);
            result = Result.success(team);
            return ActionSupport.SUCCESS;
        } catch ( Exception e ) {
            e.printStackTrace();
            result = Result.err(400, "添加失败");
            return ActionSupport.ERROR;
        }
    }

    public String updateTeam() {
        try {
            System.out.println(team);
            int i = teamService.updateTeam(team);
            if ( i > 0 ) {
                result = Result.success(team);
            } else {
                result = Result.err(300, "更新失败");
            }
            return ActionSupport.SUCCESS;
        } catch ( Exception e ) {
            e.printStackTrace();
            result = Result.err(400, "更新失败");
            return ActionSupport.ERROR;
        }
    }

    public String delTeam() {
        try {
            System.out.println(team);
            teamService.delTeam(team);
            result = Result.success(null);
            return ActionSupport.SUCCESS;
        } catch ( Exception e ) {
            e.printStackTrace();
            result = Result.err(400, "删除失败");
            return ActionSupport.ERROR;
        }
    }

    public String findByTid() {
        try {
            team = teamService.findByTid(team.getTid());
            result = Result.success(team);
            return ActionSupport.SUCCESS;
        } catch ( Exception e ) {
            result = Result.err(400, "查询失败");
            return ActionSupport.ERROR;
        }
    }
}
