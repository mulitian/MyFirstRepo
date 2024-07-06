package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.domain.pojo.Hospital;
import com.example.domain.pojo.Orders;
import com.example.domain.pojo.Setmeal;
import com.example.domain.pojo.Users;
import com.example.domain.query.UserQuery;
import com.example.domain.vo.OrderVo;
import com.example.domain.vo.PageVo;
import com.example.domain.vo.Response;
import com.example.service.HospitalService;
import com.example.service.OrdersService;
import com.example.mapper.OrdersMapper;
import com.example.service.SetmealService;
import com.example.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* @author 11963
* @description 针对表【orders】的数据库操作Service实现
* @createDate 2024-06-20 10:48:09
*/
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders>
    implements OrdersService{

    @Autowired
    private UsersService usersService;

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private SetmealService setmealService;

    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public PageVo<OrderVo> queryOrderList(UserQuery userQuery) {
        Page<OrderVo> page=new Page<>(userQuery.getPageNum(),userQuery.getPageSize());

        List<OrderVo> orderVos = ordersMapper.selectOrdersByConditions(page,userQuery);

        PageVo<OrderVo> pageVo=new PageVo<>();
        pageVo.setList(orderVos);
        pageVo.setPages(page.getPages());
        pageVo.setTotal(page.getTotal());

        return pageVo;
    }

//    @Override
//    public List<OrderVo> getAllOrders() {
//
//        List<Orders> list = this.list();
//        List<OrderVo> orderVos = new ArrayList<>();
//
//        for(Orders orders : list){
//            OrderVo orderVo=new OrderVo();
//            orderVo.setOrderid(orders.getOrderid());
//            orderVo.setUserid(orders.getUserid());
//            orderVo.setOrderdate(orders.getOrderdate());
//            orderVo.setState(orders.getState());
//
//            Users user=usersService.getById(orders.getUserid());
//            orderVo.setSex(user.getSex());
//            orderVo.setRealname(user.getRealname());
//
//            Setmeal setmeal = setmealService.getById(orders.getSmid());
//            orderVo.setSname(setmeal.getName());
//
//            Hospital hospital = hospitalService.getById(orders.getHpid());
//            orderVo.setHpname(hospital.getName());
//
//
//            orderVos.add(orderVo);
//        }
//
//        return orderVos;
//    }

    //分页查询所有订单
    @Override
    public PageVo<OrderVo> getAllOrders(Integer pageNum, Integer pageSize) {

        Page<OrderVo> page=new Page<>(pageNum,pageSize);

        List<OrderVo> orderVos = ordersMapper.selectAllOrders(page);

        PageVo<OrderVo> pageVo=new PageVo();
        pageVo.setPages(page.getPages());
        pageVo.setTotal(page.getTotal());
        pageVo.setList(orderVos);

        return pageVo;
    }
}




