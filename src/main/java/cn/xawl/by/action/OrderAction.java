package cn.xawl.by.action;

import cn.xawl.by.pojo.Orders;
import cn.xawl.by.service.OrderService;
import cn.xawl.by.utils.Result;
import cn.xawl.by.utils.Utils;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;

@Controller
public class OrderAction extends ActionSupport {
    @Autowired
    private OrderService orderService;

    private Result result;
    private Orders order = new Orders();
    private String uid;

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Orders getOrder() {
        return order;
    }

    public Result getResult() {
        return result;
    }

    public String findByOid() {
        try {
            List list = orderService.findByOid(order.getOid());
            result = Result.success(list);
            return ActionSupport.SUCCESS;
        } catch ( Exception e ) {
            e.printStackTrace();
            result = Result.err(400, "查询失败");
            return ActionSupport.ERROR;
        }
    }

    public String findByUid() {
        try {
            if ( uid == null || uid.equals("null") ) {
                result = Result.success(300, "您还未登录");
                return ActionSupport.SUCCESS;
            }
            List list = orderService.findByUid(uid);
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
            List list = orderService.findByTid(order.getTid());
            result = Result.success(list);
            return ActionSupport.SUCCESS;
        } catch ( Exception e ) {
            e.printStackTrace();
            result = Result.err(400, "查询失败");
            return ActionSupport.ERROR;
        }
    }

    /**
     * 创建订单(为订单添加订单ID设置订单创建时间 设置订单状态为1 代表订单属于创建状态)
     *
     * @return
     */
    public String createOrder() {
        try {
            //通过拦截器将cookie中 的user_id 通过反射注入到uid中 如果为null说明未登录
            if ( uid == null || uid.equals("null") ) {
                result = Result.success(300, "您还未登录");
                return ActionSupport.SUCCESS;
            }
            System.out.println(order);
            order.setUid(uid);
            order.setStatus((byte) 1);
            order.setcTime(new Date());
            order.setfTime(new Date(0));
            order.setOid(Utils.CreateID());
            order = orderService.createOrder(order);
            System.out.println(order);
            result = Result.success(order);
            return ActionSupport.SUCCESS;
        } catch ( Exception e ) {
            result = Result.err(400, "订单创建失败");
            return ActionSupport.ERROR;
        }
    }

    /**
     * 订单支付将订单状态置为2 表示订单已付款
     *
     * @return
     */
    public String POrder() {
        try {
            order.setStatus((byte) 2);
            int i = orderService.updateOrder(order);
            if ( i > 0 ) {
                result = Result.success(200, "订单支付成功", order);
            } else {
                result = Result.success(300, "订单支付失败", order);
            }
            return ActionSupport.SUCCESS;
        } catch ( Exception e ) {
            result = Result.err(400, "订单创建失败");
            return ActionSupport.ERROR;
        }
    }

    /**
     * 确认订单完成将订单状态置为2
     */
    public String FOrder() {
        try {
            order.setStatus((byte) 3);
            int i = orderService.updateOrder(order);
            if ( i > 0 ) {
                result = Result.success(200, "订单支付成功", order);
            } else {
                result = Result.success(300, "订单支付失败", order);
            }
            result = Result.success(order);
            return ActionSupport.SUCCESS;
        } catch ( Exception e ) {
            result = Result.err(400, "订单支付失败");
            return ActionSupport.ERROR;
        }
    }

}
