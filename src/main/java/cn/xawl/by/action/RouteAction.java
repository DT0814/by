package cn.xawl.by.action;

import cn.xawl.by.pojo.Route;
import cn.xawl.by.service.RouteService;
import cn.xawl.by.utils.Result;
import cn.xawl.by.utils.Utils;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class RouteAction extends ActionSupport {
    @Autowired
    private RouteService routeService;
    private Result result;
    private Route route = new Route();

    public Result getResult() {
        return result;
    }

    public Route getRoute() {
        return route;
    }

    public String findAll() {
        try {
            List<Route> list = routeService.findAll();
            result = Result.success(list);
            return ActionSupport.SUCCESS;
        } catch ( Exception e ) {
            e.printStackTrace();
            result = Result.err(400, "查询失败");
            return ActionSupport.ERROR;
        }
    }

    public String findByTid() {
        try {
            List<Route> list = routeService.findByTid(route.getTid());
            result = Result.success(list);
            return ActionSupport.SUCCESS;
        } catch ( Exception e ) {
            e.printStackTrace();
            result = Result.err(400, "查询失败");
            return ActionSupport.ERROR;
        }
    }

    public String add() {
        try {
            System.out.println(route);
            route.setRid(Utils.CreateID());
            result = Result.success(route);
            routeService.add(route);
            return ActionSupport.SUCCESS;
        } catch ( Exception e ) {
            e.printStackTrace();
            result = Result.err(400, "添加失败");
            return ActionSupport.ERROR;
        }
    }

    public String update() {
        try {
            System.out.println(route);
            int i = routeService.update(route);
            if ( i > 0 ) {
                result = Result.success(route);
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

    public String delete() {
        try {
            System.out.println(route);
            routeService.delete(route);
            result = Result.success(null);
            return ActionSupport.SUCCESS;
        } catch ( Exception e ) {
            e.printStackTrace();
            result = Result.err(400, "删除失败");
            return ActionSupport.ERROR;
        }
    }
}
