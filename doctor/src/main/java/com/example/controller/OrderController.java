package com.example.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.example.domain.pojo.Orders;
import com.example.domain.pojo.Setmeal;
import com.example.domain.pojo.Users;
import com.example.domain.query.UserQuery;
import com.example.domain.vo.OrderVo;
import com.example.domain.vo.PageVo;
import com.example.domain.vo.Response;
import com.example.service.OrdersService;
import com.example.service.SetmealService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * ClassName: OrderController
 * Package: com.example.controller
 * Description:
 *
 * @Author: Mr.zechaowei
 * @Create: 2024/6/20 - 16:19
 * @Version: v1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrdersService ordersService;

    @Autowired
    private SetmealService setmealService;


    @GetMapping("/getAllOrders")
    public Response<PageVo<OrderVo>> getAllOrders(@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        PageVo<OrderVo> page=ordersService.getAllOrders(pageNum,pageSize);
        return new Response(200,"查询成功",page);
    }

    @PostMapping("/queryOrderList")
    public Response<PageVo<OrderVo>> queryOrderList(@RequestBody UserQuery userQuery)
    {
        PageVo<OrderVo> page = ordersService.queryOrderList(userQuery);
        return new Response(200,"查询成功",page);
    }

//    @PostMapping("/queryOrderList")
//    public Response queryOrderList(@RequestBody UserQuery userQuery)
//    {
//        List<OrderVo> orderVoList = ordersService.queryOrderList(userQuery);
//        if(orderVoList.size()>userQuery.getPageSize()) {
//            List<OrderVo> orderVos = orderVoList.subList((userQuery.getPageNum() - 1) * 5, (userQuery.getPageNum() - 1) * 5 + userQuery.getPageSize());
//
//            PageDTO<OrderVo> pageDTO=new PageDTO<>();
//            pageDTO.setList(orderVos);
//            pageDTO.setTotal(orderVoList.size());
//
//            if(orderVoList.size()%userQuery.getPageSize()==0){
//                pageDTO.setPages(orderVoList.size()/userQuery.getPageSize());
//            }else{
//                pageDTO.setPages(orderVoList.size()/userQuery.getPageSize()+1);
//            }
//
//            return new Response(200, "查询成功", pageDTO);
//        }
//
//        PageDTO<OrderVo> pageDTO=new PageDTO<>();
//        pageDTO.setList(orderVoList);
//        pageDTO.setTotal(orderVoList.size());
//
//
//        if(orderVoList.size()%userQuery.getPageSize()==0){
//            pageDTO.setPages(orderVoList.size()/userQuery.getPageSize());
//        }else{
//            pageDTO.setPages(orderVoList.size()/userQuery.getPageSize()+1);
//        }
//
//        return new Response(200,"查询成功",pageDTO);
//    }


    @GetMapping("/querySmType")
    public Response querySmType(){
        List<Setmeal> list = setmealService.list();
        return new Response(200,"查询成功",list);
    }

    @GetMapping("/updateOrderState")
    public Response updateOrderState(Integer orderid){
        Orders byId = ordersService.getById(orderid);
        byId.setState(3);
        ordersService.saveOrUpdate(byId);
        return new Response(200,"修改成功",null);
    }
}
