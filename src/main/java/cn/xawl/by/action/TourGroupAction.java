package cn.xawl.by.action;

import cn.xawl.by.pojo.TourGroup;
import cn.xawl.by.service.TourGroupService;
import cn.xawl.by.utils.Result;
import cn.xawl.by.utils.Utils;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TourGroupAction extends ActionSupport {
    @Autowired
    private TourGroupService tourGroupService;

    public Result getResult() {
        return result;
    }

    public TourGroup getTourGroup() {
        return tourGroup;
    }

    private Result result;
    private TourGroup tourGroup = new TourGroup();

    public String findTourGroup() {
        try {
            List<TourGroup> list = tourGroupService.findTourGroup();
            result = Result.success(list);
            return ActionSupport.SUCCESS;
        } catch ( Exception e ) {
            result = Result.err(400, "查询失败");
            return ActionSupport.ERROR;
        }
    }

    public String add() {
        try {
            tourGroup.setTgid(Utils.CreateID());
            System.out.println(tourGroup);
            tourGroupService.add(tourGroup);
            System.out.println(tourGroup);
            result = Result.success(tourGroup);
            return ActionSupport.SUCCESS;
        } catch ( Exception e ) {
            e.printStackTrace();
            result = Result.err(400, "添加失败");
            return ActionSupport.ERROR;
        }
    }

    public String update() {
        try {
            int i = tourGroupService.update(tourGroup);
            result = Result.success(tourGroup);
            return ActionSupport.SUCCESS;
        } catch ( Exception e ) {
            e.printStackTrace();
            result = Result.err(400, "更新失败");
            return ActionSupport.ERROR;
        }
    }
}
