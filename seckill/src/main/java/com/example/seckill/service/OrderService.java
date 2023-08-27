package com.example.seckill.service;

import com.example.seckill.pojo.Order;
import com.example.seckill.pojo.User;
import com.example.seckill.vo.dto.GoodsVo;

public interface OrderService {
    Order secKill(User user, GoodsVo goodsVo);
}
