package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.domain.pojo.Cidetailedreport;
import com.example.domain.vo.CiReportVo;
import com.example.domain.vo.Response;
import com.example.service.CidetailedreportService;
import com.example.service.CireportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: CireportController
 * Package: com.example.controller
 * Description:
 *
 * @Author: Mr.zechaowei
 * @Create: 2024/6/24 - 11:25
 * @Version: v1.0
 */
@RestController
@CrossOrigin
@RequestMapping("/cireport")
public class CireportController {
    @Autowired
    private CidetailedreportService cidetailedreportService;

    @Autowired
    private CireportService cireportService;


    @GetMapping("/getCiDetail")
    public Response getCiDetail(Integer orderid) {
        List<CiReportVo> list=cireportService.getCiDetail(orderid);
        return new Response(200, "查询成功", list);
    }

//    @PostMapping("/addCiDetail")
//    public Response addCiDetail(@RequestBody Cidetailedreport cidetailedreport) {
//        cidetailedreportService.saveOrUpdate(cidetailedreport);
//        return new Response(200, "添加成功", null);
//    }

    @PostMapping("/saveCiDetail")
    public Response saveCiDetail(@RequestBody List<Cidetailedreport> cidetailedreportList) {
        for(Cidetailedreport cidetailedreport:cidetailedreportList){
            cidetailedreportService.saveOrUpdate(cidetailedreport);
        }
        return new Response(200, "保存成功", null);
    }
}
