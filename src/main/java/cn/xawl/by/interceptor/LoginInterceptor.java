package cn.xawl.by.interceptor;

import cn.xawl.by.utils.Result;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.StrutsStatics;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.net.URLDecoder;

public class LoginInterceptor extends AbstractInterceptor {
    private HttpServletRequest request;
    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        //获取action对象
        Object action = actionInvocation.getProxy().getAction();
        //获取uid的setUid方法
        Method method = action.getClass().getMethod("setUid", String.class);
        ActionContext ctx = actionInvocation.getInvocationContext();
        request = (HttpServletRequest) ctx.get(StrutsStatics.HTTP_REQUEST);
        Cookie[] cookies;
        cookies = request.getCookies();
        for ( Cookie cookie : cookies ) {
            String decode = URLDecoder.decode(cookie.getValue(), "UTF-8");
            System.out.println(cookie.getName() + ":" + decode);
            if ( cookie.getName().equals("user_id") && decode != null ) {
                method.invoke(action, decode);
            }
        }
        return actionInvocation.invoke();
    }


}
