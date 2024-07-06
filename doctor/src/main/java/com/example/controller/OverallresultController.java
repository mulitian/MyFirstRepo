package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.domain.pojo.Overallresult;
import com.example.domain.vo.Response;
import com.example.service.OverallresultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: OverallresultController
 * Package: com.example.controller
 * Description:
 *
 * @Author: Mr.zechaowei
 * @Create: 2024/6/24 - 14:54
 * @Version: v1.0
 */
@RestController
@CrossOrigin
@RequestMapping("/overallresult")
public class OverallresultController {
    @Autowired
    private OverallresultService overallresultService;

    @GetMapping("/getOverallResult")
    public Response queryResultByOrderId(Integer orderid) {
        QueryWrapper<Overallresult> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("orderId",orderid);
        List<Overallresult> overallresultList = overallresultService.list(queryWrapper);
        return new Response(200,"查询成功",overallresultList);
    }


    //更新
    @PostMapping("/saveOverallResult")
    public Response saveOverallResult(@RequestBody Overallresult overallresult) {
        overallresultService.saveOrUpdate(overallresult);
        return new Response(200,"保存成功",null);
    }

    //添加
    @PostMapping("/addOverallResult")
    public Response addOverallResult(@RequestBody Overallresult overallresult) {
        overallresultService.save(overallresult);
        return new Response(200,"添加成功",null);
    }

    //删除
    @DeleteMapping("/deleteOverallResult")
    public Response deleteOverallResult(Integer orid) {
        overallresultService.removeById(orid);
        return new Response(200,"删除成功",null);
    }

}
