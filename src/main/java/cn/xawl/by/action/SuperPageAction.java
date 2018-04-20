package cn.xawl.by.action;

import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

@Controller
public class SuperPageAction extends ActionSupport {
    private String pageName;

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    @Override
    public String execute() throws Exception {
        return pageName;
    }
}
