package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.domain.pojo.Doctor;
import com.example.service.DoctorService;
import com.example.mapper.DoctorMapper;
import org.springframework.stereotype.Service;

/**
* @author 11963
* @description 针对表【doctor】的数据库操作Service实现
* @createDate 2024-06-20 10:48:00
*/
@Service
public class DoctorServiceImpl extends ServiceImpl<DoctorMapper, Doctor>
    implements DoctorService{

}




