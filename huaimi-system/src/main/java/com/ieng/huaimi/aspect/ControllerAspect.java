package com.ieng.huaimi.aspect;

import com.google.gson.Gson;
import com.ieng.huaimi.common.utils.IPUtils;
import com.ieng.huaimi.common.utils.context.HttpContextUtils;
import com.ieng.huaimi.core.security.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Aspect
@Component
public class ControllerAspect {

    @Pointcut("execution(* com.ieng.huaimi..*.controller..*.*(..))")
    public void pointCut(){}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object result = point.proceed();
        long time = System.currentTimeMillis() - beginTime;

        pointLog(point, result, time);

        return result;
    }

    private void pointLog(ProceedingJoinPoint joinPoint, Object result, long time) throws Exception {
        StringBuilder builder = new StringBuilder();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        String name = joinPoint.getTarget().getClass().getName();
        builder.append("START Controller Aspect START\n");
        builder.append("==>\tHeader: ").append(name).append("::").append(method.getName()).append('\n');

        HttpServletRequest request = HttpContextUtils.getRequest();
        assert request != null;
        String uri = request.getRequestURI();
        builder.append("==>\tRequest:\n");
        builder.append("<==\t\t IP: ").append(IPUtils.getIpAddr(request)).append('\n');
        builder.append("<==\t\t URI: ").append(uri).append('\n');
        builder.append("<==\t\t Method: ").append(request.getMethod()).append('\n');

       try{
           String username = SecurityUtils.getUsername();
           builder.append("<==\t\t User: ").append(username).append("\n");
       } catch (Exception ignored){}

        Object[] args = joinPoint.getArgs();

        if(args != null && args.length > 0){
            builder.append("<==\t\t Parameters: ");
            for (Object arg : args) {
                Class<?> argClass = arg.getClass();
                builder.append(argClass.getSimpleName()).append("'");
                Field[] fields = argClass.getDeclaredFields();
                for (Field field : fields) {
                    if("serialVersionUID".equals(field.getName())) continue;
                    field.setAccessible(true);
                    builder.append('(');//.append(field.getType().getSimpleName())
                    builder.append(field.getName()).append(":");
                    builder.append(field.get(arg)).append(')');
                }
                builder.append("'\t");
            }
            builder.append('\n');
        }

        builder.append("<==\tResponse: ");

        builder.append(new Gson().toJson(result));

        /*String json = new GsonBuilder().setPrettyPrinting().create().toJson(result);
        builder.append("\n==>\n").append(json);*/

        builder.append('\n').append("Execute Time: ").append(time).append("ms");

        builder.append('\n').append("END Controller Aspect END");
        System.out.println(builder);
    }

}
