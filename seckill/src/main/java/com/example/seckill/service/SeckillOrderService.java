package com.example.seckill.service;

import com.example.seckill.pojo.User;

public interface SeckillOrderService {
    Long getResult(User user, Long goodsId);
}
