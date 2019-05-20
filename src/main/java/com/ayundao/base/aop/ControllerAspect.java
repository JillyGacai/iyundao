package com.ayundao.base.aop;

import com.ayundao.base.annotation.Permission;
import com.ayundao.service.RedisServcie;
import com.ayundao.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * @ClassName: ControllerAspect
 * @project: ayundao
 * @author: 念
 * @Date: 2019/5/18 14:12
 * @Description: 控制类--切面
 * @Version: V1.0
 */
@Aspect
@Component
@ConfigurationProperties(prefix = "author")
public class ControllerAspect {

    private final static Logger log = LoggerFactory.getLogger(ControllerAspect.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RedisServcie redisServcie;

    private List<String> normal = new ArrayList<>();

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(UUID.randomUUID().toString().replace("-", ""));
        }
    }

    /**
     *
     */
    @Pointcut("@annotation(permission)")
    public void author(Permission permission) {
    }

    /**
     * 权限环绕通知
     * @param joinPoint
     * @throws Throwable
     */
    @ResponseBody
    @Around("author(permission)")
    public Object isAccessMethod(ProceedingJoinPoint joinPoint, Permission permission) {
        System.out.println("---------方法执行之前-------------");
        try {
            HttpServletRequest req = getCurrentRequest();
            boolean flag = false;
            String account = "";
            for (String s : normal) {
                if (s.equals(req.getRequestURI())) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                account = req.getAttribute("account") != null
                        ? req.getAttribute("account").toString()
                        : account;
                if (StringUtils.isNotBlank(account)) {

                }
            }
            // 执行原方法，并记录返回值。
//            if (StringUtils.isBlank(account)) {
//                return "index";
//            }
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("---------方法执行之后-------------");
        return null;
    }

    /**
     * 获取当前request
     * @return
     */
    private HttpServletRequest getCurrentRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        return request;
    }
}
