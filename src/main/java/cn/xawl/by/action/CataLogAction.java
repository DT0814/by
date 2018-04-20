package cn.xawl.by.action;

import cn.xawl.by.pojo.Catalog;
import cn.xawl.by.service.CataLogService;
import cn.xawl.by.utils.Result;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CataLogAction extends ActionSupport {
    @Autowired
    private CataLogService cataLogService;
    public Result result;
    public Catalog catalog = new Catalog();

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public Result getResult() {
        return result;
    }

    public String findAll() {
        try {
            List list = cataLogService.findAll();
            result = Result.success(list);
            return ActionSupport.SUCCESS;
        } catch ( Exception e ) {
            result = Result.err(400, "查询失败");
            return ActionSupport.ERROR;
        }
    }

    public String add() {
        try {
            System.out.println(catalog);
            result = Result.success(catalog);
            cataLogService.add(catalog);
            return ActionSupport.SUCCESS;
        } catch ( Exception e ) {
            e.printStackTrace();
            result = Result.err(400, "添加失败");
            return ActionSupport.ERROR;
        }
    }

    public String update() {
        try {
            System.out.println(catalog);
            int i = cataLogService.update(catalog);
            if ( i > 0 ) {
                result = Result.success(catalog);
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
            System.out.println(catalog);
            cataLogService.delete(catalog);
            result = Result.success(null);
            return ActionSupport.SUCCESS;
        } catch ( Exception e ) {
            e.printStackTrace();
            result = Result.err(400, "删除失败");
            return ActionSupport.ERROR;
        }
    }

}
