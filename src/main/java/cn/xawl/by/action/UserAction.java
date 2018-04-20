package cn.xawl.by.action;

import cn.xawl.by.pojo.Users;
import cn.xawl.by.service.UserService;
import cn.xawl.by.utils.Result;
import cn.xawl.by.utils.Utils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
public class UserAction extends ActionSupport {
    @Autowired
    private UserService userService;

    private Result result;

    public Users getUser() {
        return user;
    }

    private Users user = new Users();

    public Result getResult() {
        return result;
    }


    /* public void setAccount(String account) {
         this.user.setAccount(account);
     }

     public void setPassword(String password) {
         this.user.setPassword(password);
     }*/

    public String findByUid() {
        try {
            Users u = userService.findByUid(user.getUid());
            result = Result.success(u);
            return ActionSupport.SUCCESS;
        } catch ( Exception e ) {
            e.printStackTrace();
            result = Result.err(400, "查询失败");
            return ActionSupport.ERROR;
        }
    }

    public String register() {
        try {
            System.out.println(user);
            //校验 未完成
            user.setUid(Utils.CreateID());
            user.setStatus((byte) 2);
            user.setPassword(user.getPassword().split(",")[0]);
            user = userService.addUser(user);
            result = Result.success(user);
            return ActionSupport.SUCCESS;
        } catch ( Exception e ) {
            e.printStackTrace();
            result = Result.err(400, "注册失败请联系管理员");
            return ActionSupport.ERROR;
        }
    }

    /***
     * 查询所有用户
     *
     * @return
     */
    public String findAllUser() {
        try {
            List<Users> list = userService.findAll();
            list.stream().forEach(n -> {
                System.out.print(n);
            });
            result = Result.success(list);
            return ActionSupport.SUCCESS;
        } catch ( Exception e ) {
            result = Result.err(400, "查询失败");
            return ActionSupport.ERROR;
        }
    }

    /***
     * 添加用户(用户注册)
     *
     * @return
     */
    public String addUser() {
        try {
            user.setUid(Utils.CreateID());
            user.setStatus((byte) 2);
            user = userService.addUser(user);
            result = Result.success(user);
            return ActionSupport.SUCCESS;
        } catch ( Exception e ) {
            e.printStackTrace();
            result = Result.err(400, "插入失败");
            return ActionSupport.ERROR;
        }
    }

    /***
     * 更新用户
     *
     * @return
     */
    public String updateUser() {
        try {
            System.out.println(user);
            int i = userService.updateUser(user);
            if ( i > 0 ) {
                result = Result.success(user);
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

    /**
     * 根据主键删除用户
     *
     * @return
     */
    public String delUser() {
        try {
            System.out.println(user);
            user = userService.delUser(user);
            result = Result.success(user);
            return ActionSupport.SUCCESS;
        } catch ( Exception e ) {
            e.printStackTrace();
            result = Result.err(400, "删除失败");
            return ActionSupport.ERROR;
        }
    }

    public String login() {
        try {
            System.out.println(user);
            Users u = userService.findByAccount(user.getAccount());
            if ( u == null ) {
                result = Result.err(300, "账号不存在");
            } else if ( user.getPassword().equals(u.getPassword()) ) {
                ActionContext context = ActionContext.getContext();
                Map<String, Object> session = context.getSession();
                // request.getSession().setAttribute("user", u);
                session.put("user", u);
                result = Result.success(u);
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
