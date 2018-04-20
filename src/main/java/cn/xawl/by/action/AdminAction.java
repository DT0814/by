package cn.xawl.by.action;

import cn.xawl.by.pojo.Admin;
import cn.xawl.by.service.AdminService;
import cn.xawl.by.utils.Result;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AdminAction extends ActionSupport {
    @Autowired
    private AdminService adminService;


    private Admin admin = new Admin();

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    private Result result;

    public Result getResult() {
        return result;
    }

    public String login() {
        try {
            System.out.println(admin);
            Admin a = adminService.findByAccount(admin.getAnum());
            if ( a == null ) {
                result = Result.err(300, "账号不存在");
            } else if ( admin.getApss().equals(a.getApss()) ) {
                result = Result.success(a);
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

}
