package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.domain.pojo.Hospital;
import com.example.service.HospitalService;
import com.example.mapper.HospitalMapper;
import org.springframework.stereotype.Service;

/**
* @author 11963
* @description 针对表【hospital】的数据库操作Service实现
* @createDate 2024-06-20 10:48:02
*/
@Service
public class HospitalServiceImpl extends ServiceImpl<HospitalMapper, Hospital>
    implements HospitalService{

}




