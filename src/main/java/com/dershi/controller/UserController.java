package com.dershi.controller;

import com.dershi.pojo.User;
import com.dershi.service.UserServiceImpl;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

@Controller
public class UserController {
    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    /**
    *@author Dershi
    *@Description 用户注册
    *@Date 2022/1/1 16:30
    *@Param [username, password, email]
    */
    @RequestMapping(value = "/userRegister", method = RequestMethod.POST)
    protected ModelAndView register(String username,String password,String email, String code, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        // 获取Session中的验证码
        String token = (String) session.getAttribute(KAPTCHA_SESSION_KEY);
        session.removeAttribute(KAPTCHA_SESSION_KEY);
        int addUserFlag = 0;
        // 用户名不存在，进行注册添加
        if (token != null && token.equalsIgnoreCase(code)) {
            if (!userService.queryUser(username)) {
                User user = new User(username, password, email);
                addUserFlag = userService.addUser(user);
                if (addUserFlag > 0) {
                    mv.addObject("registerMsg", "注册成功");
                    mv.setViewName("user/register");
                } else {
                    mv.addObject("registerMsg", "用户名已存在");
                    mv.setViewName("user/register");
                }
            }
        } else {
            mv.addObject("codeMsg", "验证码错误");
            mv.addObject("username", username);
            mv.addObject("email", email);
            mv.setViewName("user/register");
        }
        return mv;
    }

    /**
    *@author Dershi
    *@Description 用户登录
    *@Date 2022/1/1 16:30
    *@Param [username, password, session]
    */
    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    protected ModelAndView login(String username,String password, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        // 先判断用户是否存在允许登录
        if (userService.queryUser(username)) {
            // 密码匹配成功，允许登录
            if (userService.queryUser(username, password)) {
                session.setAttribute("username", username);
                session.setAttribute("loginInfo", username);
                session.setAttribute("loginMsg", 1);
                mv.setViewName("redirect:/");
            } else {
                mv.addObject("username", username);
                mv.addObject("loginMsg", "密码错误");
                mv.setViewName("user/login");
            }
        } else {
            mv.addObject("loginMsg", "用户不存在");
            mv.setViewName("user/login");
        }
        return mv;
    }

    /**
    *@author Dershi
    *@Description 用户注销
    *@Date 2022/1/4 15:51
    *@Param [session]
    */
    @RequestMapping("/userLogout")
    protected String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping("/ajaxExistsUsername")
    @ResponseBody
    protected String ajaxExistsUsername(String username) {
        boolean b = userService.queryUser(username);
        Map<String, Object> map = new HashMap<>();
        map.put("checkUsername", b);
        return new Gson().toJson(map);
    }

    @RequestMapping("/ajaxCode")
    @ResponseBody
    protected String ajaxCode(String code, HttpSession session) {
        // 获取Session中的验证码
        String token = (String) session.getAttribute(KAPTCHA_SESSION_KEY);
        boolean b = token != null && token.equalsIgnoreCase(code);
        Map<String, Object> map = new HashMap<>();
        map.put("checkCode", b);
        return new Gson().toJson(map);
    }

    @RequestMapping("/loginStatus")
    @ResponseBody
    protected String loginStatus(HttpSession session) {
        String loginInfo = (String) session.getAttribute("loginInfo");
        boolean b = loginInfo != null;
        Map<String, Object> map = new HashMap<>();
        map.put("loginStatus", b);
        return new Gson().toJson(map);
    }
}
