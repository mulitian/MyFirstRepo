package com.example.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.domain.pojo.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.domain.query.UserQuery;
import com.example.domain.vo.OrderVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 11963
* @description 针对表【orders】的数据库操作Mapper
* @createDate 2024-06-20 10:48:09
* @Entity com.example.domain.pojo.Orders
*/
public interface OrdersMapper extends BaseMapper<Orders> {
    List<OrderVo> selectOrdersByConditions(@Param("page") IPage<OrderVo> page,@Param("userQuery") UserQuery userQuery);

    List<OrderVo> selectAllOrders(IPage<OrderVo> page);
}




