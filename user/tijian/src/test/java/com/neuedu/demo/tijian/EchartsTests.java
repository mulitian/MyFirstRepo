package com.neuedu.demo.tijian;

import com.neuedu.demo.tijian.pojo.Setmeal;
import com.neuedu.demo.tijian.service.SetmealService;
import com.neuedu.demo.tijian.service.impl.EchartsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class EchartsTests {
    @Autowired
    private EchartsServiceImpl echartsService;
    @Autowired
    private SetmealService setmealService;
    @Test
    public void testGetHpsData() {
        System.out.println(echartsService.getHpsData());
    }

    @Test
    public void testGetMealsData() {
//        System.out.println(echartsService.getMealsData(1));
        System.out.println(echartsService.getMealsData(2, 1));
    }

}
