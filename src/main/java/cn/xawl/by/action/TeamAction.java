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
            team.setTid("2172659241879");
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
}
