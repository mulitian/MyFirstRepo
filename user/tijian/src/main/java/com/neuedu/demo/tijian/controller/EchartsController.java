package com.neuedu.demo.tijian.controller;

import com.neuedu.demo.tijian.dto.Response;
import com.neuedu.demo.tijian.service.EchartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class EchartsController {
    @Autowired
    private EchartsService echartsService;

    @GetMapping("/echarts/getHpsData")
    public Response getHpsData() {
        return Response.success(echartsService.getHpsData());
    }

    @GetMapping("/echarts/getMealsData")
    public Response getMealsData(Integer hpId, Integer type) {
        return Response.success(echartsService.getMealsData(hpId, type));
    }
}
