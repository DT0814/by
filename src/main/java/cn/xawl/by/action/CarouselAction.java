package cn.xawl.by.action;

import cn.xawl.by.pojo.Carousel;
import cn.xawl.by.service.CarouselService;
import cn.xawl.by.utils.Result;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.util.List;

@Controller
public class CarouselAction extends ActionSupport {
    @Autowired
    private CarouselService carouselService;
    private Result result;
    private Carousel carousel = new Carousel();

    private File file;


    public String findByTgid() {
        try {
            List list = carouselService.findByTgid(carousel.getTgid());
            result = Result.success(list);
            return ActionSupport.SUCCESS;
        } catch ( Exception e ) {
            e.printStackTrace();
            result = Result.err(400, "查询失败");
            return ActionSupport.ERROR;
        }
    }


    public void setFile(File file) {
        this.file = file;
    }

    public Result getResult() {
        return result;
    }

    public Carousel getCarousel() {
        return carousel;
    }

    public void setCarousel(Carousel carousel) {
        this.carousel = carousel;
    }
}
