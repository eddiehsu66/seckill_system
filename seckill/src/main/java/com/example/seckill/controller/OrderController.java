package com.example.seckill.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@Api(value = "订单", tags = "订单")
public class OrderController {

//    @Autowired
//    private ITOrderService itOrderService;
//
//
//    @ApiOperation("订单")
//    @RequestMapping(value = "/detail", method = RequestMethod.GET)
//    @ResponseBody
//    public RespBean detail(TUser tUser, Long orderId) {
//        if (tUser == null) {
//            return RespBean.error(RespBeanEnum.SESSION_ERROR);
//        }
//        OrderDeatilVo orderDeatilVo = itOrderService.detail(orderId);
//        return RespBean.success(orderDeatilVo);
//    }
}