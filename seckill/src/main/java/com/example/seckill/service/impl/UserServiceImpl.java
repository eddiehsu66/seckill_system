package com.example.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.seckill.mapper.UserMapper;
import com.example.seckill.pojo.User;
import com.example.seckill.service.UserService;
import com.example.seckill.utils.MD5Util;
import com.example.seckill.exception.GlobalException;
import com.example.seckill.utils.UUIDUtil;
import com.example.seckill.vo.LoginVo;
import com.example.seckill.vo.RespBean;
import com.example.seckill.vo.RespBeanEnum;
import com.example.seckill.utils.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper usermapper;

    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public RespBean doLogin(LoginVo loginVo,HttpServletRequest request,HttpServletResponse response){
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
//        参数校验采用注解来写成
//        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)){
//            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
//        }
//        if (!ValidatorUtil.isMobile(mobile)){
//            return RespBean.error(RespBeanEnum.MOBILE_ERROR);
//        }
        User user = usermapper.selectById(mobile);
        if (user == null){
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
       }
        if (!MD5Util.fromPassToDBPass(password,user.getSalt()).equals(user.getPassword())){
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }
        String ticket = UUIDUtil.uuid();
        redisTemplate.opsForValue().set("user:"+ticket,user);
//        request.getSession().setAttribute(ticket,user);
        CookieUtil.setCookie(request,response,"userTicket",ticket);
        return RespBean.success();
    }

    public User getUserByCookie(String userTicket,HttpServletRequest request,HttpServletResponse response){
        if (StringUtils.isEmpty(userTicket)){
            return null;
        }
        User user = (User)redisTemplate.opsForValue().get("user:"+userTicket);
        if (user !=null){
            CookieUtil.setCookie(request,response,"userTicket",userTicket);
        }
        return user;
    }
}
