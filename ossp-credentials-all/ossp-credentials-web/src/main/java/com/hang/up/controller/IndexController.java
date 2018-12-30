package com.hang.up.controller;

import com.alibaba.druid.util.StringUtils;
import com.hang.up.model.*;
import com.hang.up.service.ManagerService;
import com.hang.up.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 顺 on 2018/8/11.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private ManagerService managerService;

    private static final String  SKEY    = "MengLian";
    private static final Charset CHARSET = Charset.forName("gb2312");

    private static Map<String,HttpSession> sessionMap = new HashMap<>();

    @RequestMapping("")
    public String home() {
        return "/index";
    }

    @RequestMapping("/mblrLogin")
    public String login() {
        return "/mblrLogin";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public  Map<String,String> login(@RequestBody User user , HttpSession session){
        Map<String,String> map  = new HashMap<>();
        if(user.getUserName()!=null&&user.getPassword()!=null){
            String encryptResult = DesUtil.encrypt(user.getPassword(), CHARSET, SKEY);
            User userObj =  managerService.getUserByUserName(user.getUserName());
            if(userObj!=null&&encryptResult.equals(userObj.getPassword())){
                if(sessionMap.get(user.getUserName())!=null){
                    try {
                        sessionMap.get(user.getUserName()).invalidate();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                //将用户对象添加到Session中
                session.setAttribute("loginUser",user);
                //设置单点登录
                sessionMap.put(user.getUserName(),session);
                //重定向到主页面的跳转方法
                map.put("code","200");
                map.put("path","/manager");
                map.put("name",userObj.getTrueName());
                return map;
            }
        }
        map.put("code","300");
        map.put("err","用户名或密码错误");
        return map;
    }

    @RequestMapping(value = "/logout")
    @ResponseBody
    public Map<String,String> logout(HttpSession session){
        //清除session
        User user = (User) session.getAttribute("loginUser");
        sessionMap.remove(user.getUserName());
        session.invalidate();
        Map<String,String> map  = new HashMap<>();
        map.put("code","/mblrLogin");
        return map;
    }

    @RequestMapping(value = "/getHomeList")
    @ResponseBody
    public List<Home> getHomeList(){
        return managerService.getHomeAll();
    }

    @RequestMapping(value = "/getAboutUs")
    @ResponseBody
    public AboutUs getAboutUs(){
        return managerService.getAboutUs();
    }

    @RequestMapping(value = "/getProductPage")
    @ResponseBody
    public Page getProductPage(@RequestBody Page page){
        return managerService.getProductPage(page);
    }

    @RequestMapping(value = "/getInformationPage")
    @ResponseBody
    public Page getInformationPage(@RequestBody Page page){
        return managerService.getInformationPage(page);
    }

    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    @ResponseBody
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CaptchaUtil.outputCaptcha(request, response);
    }

    @RequestMapping(value = "/saveMessage", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult saveMessage(@RequestBody Message message,HttpSession session) throws ServletException, IOException {
        if(StringUtils.equals(message.getCode().toLowerCase(),session.getAttribute("randomString").toString().toLowerCase())){
            managerService.saveMessage(message);
            return new BaseResult();
        }
        return new BaseResult(ResultCode.CODE_ERROR);
    }

    @RequestMapping(value = "/getProductById")
    @ResponseBody
    public Product getProductById(Integer id){
        return managerService.getProductById(id);
    }

    @RequestMapping(value = "/getInformationById")
    @ResponseBody
    public Information getInformationById(Integer id){
        return managerService.getInformationById(id);
    }

    @RequestMapping(value = "/getContactUs")
    @ResponseBody
    public ContactUs getContactUs(){
        return managerService.getContactUs();
    }
}
