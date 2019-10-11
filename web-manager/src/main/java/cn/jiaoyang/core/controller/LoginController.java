package cn.jiaoyang.core.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther Jiao yang
 * @Date 2019/9/27 19:28
 * 登录
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @RequestMapping("/showName")
    public Map showName(){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        HashMap map = new HashMap();
        map.put("username",userName);
        return map;
    }
}
