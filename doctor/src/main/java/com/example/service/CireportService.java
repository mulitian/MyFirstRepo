package com.example.service;

import com.example.domain.pojo.Cidetailedreport;
import com.example.domain.pojo.Cireport;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.domain.vo.CiReportVo;

import java.util.List;

/**
* @author 11963
* @description 针对表【cireport】的数据库操作Service
* @createDate 2024-06-20 10:47:57
*/
public interface CireportService extends IService<Cireport> {


     List<CiReportVo> getCiDetail(Integer orderid);
}
