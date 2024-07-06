package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.domain.pojo.Cidetailedreport;
import com.example.domain.pojo.Cireport;
import com.example.domain.vo.CiReportVo;
import com.example.service.CidetailedreportService;
import com.example.service.CireportService;
import com.example.mapper.CireportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author 11963
* @description 针对表【cireport】的数据库操作Service实现
* @createDate 2024-06-20 10:47:57
*/
@Service
public class CireportServiceImpl extends ServiceImpl<CireportMapper, Cireport>
    implements CireportService{

    @Autowired
    private CidetailedreportService cidetailedreportService;

    @Override
    public List<CiReportVo> getCiDetail(Integer orderid) {
        QueryWrapper<Cireport> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("orderId",orderid);
        List<Cireport> cireports= this.list(queryWrapper);
        List<CiReportVo> ciReportVos=new ArrayList<>();
        System.out.println(cireports);

        for(Cireport cireport:cireports){
            CiReportVo ciReportVo=new CiReportVo();
            ciReportVo.setOrderid(orderid);
            ciReportVo.setCiName(cireport.getCiname());
            ciReportVo.setCiid(cireport.getCiid());
            ciReportVo.setCirid(cireport.getCirid());

            QueryWrapper<Cidetailedreport> queryWrapper1=new QueryWrapper<>();
            queryWrapper1.eq("orderId",orderid)
                    .eq("ciid",cireport.getCiid());
//            System.out.println(orderid);
//            System.out.println(cireport.getCiid());
            List<Cidetailedreport> cidetailedreports=cidetailedreportService.list(queryWrapper1);
//            System.out.println("cidetailedreports = " + cidetailedreports);

            ciReportVo.setCidetailedreport(cidetailedreports);
            ciReportVos.add(ciReportVo);
        }

        return ciReportVos;
    }
}




