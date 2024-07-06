package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.domain.pojo.Doctor;
import com.example.domain.pojo.Setmeal;
import com.example.domain.vo.Response;
import com.example.service.DoctorService;
import com.example.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: DoctorController
 * Package: com.example.controller
 * Description:
 *
 * @Author: Mr.zechaowei
 * @Create: 2024/6/20 - 14:58
 * @Version: v1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;


    @PostMapping("/login")
    public Response login(@RequestBody Doctor doctor){
        QueryWrapper<Doctor> queryWrapper = new QueryWrapper();
        queryWrapper.eq("docCode",doctor.getDoccode());

        Doctor doc = doctorService.getOne(queryWrapper);
        if(doc==null||!doc.getPassword().equals(doctor.getPassword())){
            return new Response(401,"账号或密码错误",null);
        }
        return new Response(200,"登陆成功",doc);
    }

    @PostMapping("/changePassword")
    public Response changePassword(@RequestBody Doctor doctor){
        doctorService.saveOrUpdate(doctor);
        return new Response(200,"修改成功",null);
    }

}
