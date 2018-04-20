package cn.xawl.by.action;

import cn.xawl.by.pojo.Ciceroni;
import cn.xawl.by.service.CiceroniService;
import cn.xawl.by.utils.Result;
import cn.xawl.by.utils.Utils;
import com.alibaba.fastjson.annotation.JSONField;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CiceroniAction extends ActionSupport {
    @Autowired
    private CiceroniService ciceroniService;

    private Result result;
    @JSONField( serialize = false )
    private Ciceroni ciceroni = new Ciceroni();

    public Result getResult() {
        return result;
    }

    public Ciceroni getCiceroni() {
        return ciceroni;
    }

    public String findAll() {
        try {
            List<Ciceroni> list = ciceroniService.findAll();
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
            List<Ciceroni> list = ciceroniService.findByTid(ciceroni.getTid());
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
            System.out.println(ciceroni);
            ciceroni.setCid(Utils.CreateID());
            ciceroni.setStatus((byte) 2);
            ciceroni = ciceroniService.add(ciceroni);
            result = Result.success(ciceroni);
            return ActionSupport.SUCCESS;
        } catch ( Exception e ) {
            e.printStackTrace();
            result = Result.err(400, "添加失败");
            return ActionSupport.ERROR;
        }
    }

    public String update() {
        try {
            System.out.println(ciceroni);
            int i = ciceroniService.update(ciceroni);
            if ( i > 0 ) {
                result = Result.success(ciceroni);
            } else {
                result = Result.err(201, "更新失败");
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
            System.out.println(ciceroni);
            ciceroniService.delete(ciceroni);
            result = Result.success(ciceroni);
            return ActionSupport.SUCCESS;
        } catch ( Exception e ) {
            e.printStackTrace();
            result = Result.err(400, "删除失败");
            return ActionSupport.ERROR;
        }
    }
}
