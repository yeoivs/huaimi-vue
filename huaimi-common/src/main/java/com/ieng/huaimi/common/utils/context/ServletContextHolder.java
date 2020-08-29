package com.ieng.huaimi.common.utils.context;

import com.ieng.huaimi.common.utils.JSONUtil;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class ServletContextHolder {

    public static void sendJSON(HttpServletResponse response, Object obj){
        response.setContentType("application/json; charset=UTF-8");

        try(PrintWriter out = response.getWriter()){
            out.print(JSONUtil.toJsonString(obj));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static HttpServletRequest getRequest(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
        return attributes != null ? attributes.getRequest() : null;
    }

}
