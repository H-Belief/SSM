package com.belief.controller;

import com.belief.model.User;
import com.belief.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ConfigurableWebApplicationContext;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Resource
    private ConfigurableWebApplicationContext wac;


    @Value("test")
    private String test;


    @RequestMapping(value = "/showUser", method = RequestMethod.POST)
    public void selectUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        long userId = Long.parseLong(request.getParameter("id"));
        User user = this.userService.selectUser(userId);
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(user));
        response.getWriter().close();
    }


    @RequestMapping(value = "/user")
    @ResponseBody
    public User selectUser(Integer id) {


        User user = this.userService.selectUser(id);

        return user;
    }



    @RequestMapping("/getProperties")
    public void seleceUser(){


        System.out.println(test);
    }


    public int regresh(){
        try{
            wac.refresh();
            return 1;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }


    public static void main(String[] args) {
        String arr = "12,60,59,43,43,43,43,66";
        String brr = "B,C,A,A,A,A,A,E";
        String[] asplit = arr.split(",");
        String[] bsplit = brr.split(",");
        String a = "";
        StringBuilder sba = new StringBuilder();
        StringBuilder sbb = new StringBuilder();
        for (int i = 0; i < asplit.length; i++) {
            String ans = asplit[i];
            String choose = bsplit[i];
            if (a.equals(ans)) {
                continue;
            } else {
                sba.append(ans);
                sbb.append(choose);
                a = ans;
            }
        }
        System.out.println(sba.toString());
        System.out.println(sbb.toString());
    }

}