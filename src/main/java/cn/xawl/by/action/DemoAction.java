package cn.xawl.by.action;

import cn.xawl.by.pojo.User;
import cn.xawl.by.service.DemoService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DemoAction extends ActionSupport {
    @Autowired
    private DemoService demoService;

    public String login() {
        System.out.println("success");
        List<User> all = demoService.findAll();
        all.stream().forEach((e) -> {
            System.out.println(e);
        });
        return "success";
    }
}
