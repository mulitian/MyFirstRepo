package com.example.tijiandocker;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.domain.pojo.Checkitem;
import com.example.domain.pojo.Doctor;
import com.example.domain.pojo.Orders;
import com.example.service.CheckitemService;
import com.example.service.DoctorService;
import com.example.service.OrdersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TijianDockerApplicationTests {
    @Autowired
    private CheckitemService checkitemService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private OrdersService ordersService;

    @Test
    void contextLoads() {
        Checkitem byId = checkitemService.getById(1);
        System.out.println("byId = " + byId);
    }

    @Test
    void test1(){
        Doctor byId = doctorService.getById(1);
        System.out.println("byId = " + byId);
    }

    @Test
    void testPage(){
        Page<Orders> page=new Page<>(1,5);
        ordersService.page(page);
        List<Orders> records=page.getRecords();
        System.out.println(records.size());
    }
}
