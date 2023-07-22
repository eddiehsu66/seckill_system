package com.example.seckill.controller;

import com.example.seckill.pojo.User;

import com.example.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CookieValue;
import org.thymeleaf.util.StringUtils;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Controller
@RequestMapping("/goods")

public class GoodsController {
    @Autowired
    UserService userservice;
    @RequestMapping("/toList")
//    public String toList(HttpServletRequest request,HttpServletResponse response,Model model,@CookieValue("userTicket") String ticket){
    public String toList(Model model,User user){
//        if (StringUtils.isEmpty(ticket)){
//            return "login";
//        }
////        User user = (User)session.getAttribute(ticket);
//        User user = userservice.getUserByCookie(ticket,request,response);
//        if (user == null){
//            return "login";
//        }
        model.addAttribute("user",user);
        return "goodsList";
    }
}
