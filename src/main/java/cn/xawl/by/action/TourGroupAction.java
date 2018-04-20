package cn.xawl.by.action;

import cn.xawl.by.pojo.TGResult;
import cn.xawl.by.pojo.TourGroup;
import cn.xawl.by.service.TourGroupService;
import cn.xawl.by.utils.FileUtil;
import cn.xawl.by.utils.Result;
import cn.xawl.by.utils.Utils;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import java.io.*;

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

    public void setTourGroup(TourGroup tourGroup) {
        this.tourGroup = tourGroup;
    }

    private TourGroup tourGroup = new TourGroup();

    public File file;
    private String fileContentType; //得到文件的类型
    private String fileFileName; //得到文件的名称

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String findByTid() {
        try {
            System.out.println(tourGroup.getTgid());
            if ( tourGroup.getTid() != null && !tourGroup.getTid().equals("") ) {
                List list = tourGroupService.findByTid(tourGroup.getTid());
                result = Result.success(list);
            } else {
                result = Result.err(300, "Tid不能为空");
            }
            return ActionSupport.SUCCESS;
        } catch ( Exception e ) {
            e.printStackTrace();
            result = Result.err(400, "查询失败");
            return ActionSupport.ERROR;
        }
    }

    /**
     * 根据tgid查找
     */
    public String findByTgid() {
        try {
            System.out.println(tourGroup.getTgid());
            if ( tourGroup.getTgid() != null && !tourGroup.getTgid().equals("") ) {
                List list = tourGroupService.findByTgid(tourGroup.getTgid());
                result = Result.success(list);
            } else {
                result = Result.err(300, "Tgid不能为空");
            }
            return ActionSupport.SUCCESS;
        } catch ( Exception e ) {
            e.printStackTrace();
            result = Result.err(400, "查询失败");
            return ActionSupport.ERROR;
        }
    }

    /**
     * 根据价格查找
     *
     * @return
     */
    public String findByPrice() {
        try {
            System.out.println(tourGroup.getFindCondition());
            JSONObject object = JSONObject.parseObject(tourGroup.getFindCondition());
            String sInputAmount = object.getString("SInputAmount");
            String bInputAmount = object.getString("BInputAmount");
            System.out.println("SInputAmount" + sInputAmount + "BInputAmount" + bInputAmount);
            String pattern = "[0-9]*";
            boolean s = Pattern.matches(pattern, sInputAmount);
            boolean b = Pattern.matches(pattern, bInputAmount);
            if ( !s || !b ) {
                result = Result.err(300, "请输入正确的价格格式");
                return ActionSupport.ERROR;
            }
            List list = tourGroupService.findByPrice(sInputAmount, bInputAmount);
            result = Result.success(list);
            return ActionSupport.SUCCESS;
        } catch ( JSONException js ) {
            js.printStackTrace();
            result = Result.err(300, "请输入正确的价格格式");
            return ActionSupport.ERROR;
        } catch ( Exception e ) {
            e.printStackTrace();
            result = Result.err(400, "查询失败");
            return ActionSupport.ERROR;
        }
    }

    /**
     * 根据出发地目的地查找
     *
     * @return
     */
    public String findByName() {
        try {
            System.out.println(tourGroup.getName());
            List list = tourGroupService.findByName(tourGroup.getName());
            result = Result.success(list);
            return ActionSupport.SUCCESS;
        } catch ( Exception e ) {
            e.printStackTrace();
            result = Result.err(400, "查询失败");
            return ActionSupport.ERROR;
        }

    }

    public String findTourGroup() {
        try {
            List<TGResult> list = tourGroupService.findTourGroup();
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
            HttpServletRequest request = ServletActionContext.getRequest();
            String path = request.getSession().getServletContext().getRealPath("images") + "/" + new SimpleDateFormat("yyyyMMdd").format(new Date());
            String img = FileUtil.upload(file, path, fileFileName.substring(fileFileName.indexOf(".")));
            tourGroup.setTgid(Utils.CreateID());
            tourGroup.setImg("images/" + path.split("/")[path.split("/").length - 1] + "/" + img);
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

    /**
     * 根据分类查找
     *
     * @return
     */
    public String findbyCaid() {
        try {
            List list = tourGroupService.findbyCaid(tourGroup.getCaid());
            result = Result.success(list);
            return ActionSupport.SUCCESS;
        } catch ( Exception e ) {
            e.printStackTrace();
            result = Result.err(400, "查询失败");
            return ActionSupport.ERROR;
        }
    }

}
