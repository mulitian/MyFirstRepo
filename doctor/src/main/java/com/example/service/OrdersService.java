package com.example.service;

import com.example.domain.pojo.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.domain.pojo.Users;
import com.example.domain.query.UserQuery;
import com.example.domain.vo.OrderVo;
import com.example.domain.vo.PageVo;

import java.util.List;

/**
* @author 11963
* @description 针对表【orders】的数据库操作Service
* @createDate 2024-06-20 10:48:09
*/
public interface OrdersService extends IService<Orders> {

    PageVo<OrderVo> queryOrderList(UserQuery userQuery);

//    List<OrderVo> getAllOrders();
    PageVo<OrderVo> getAllOrders(Integer pageNum, Integer pageSize);
}
