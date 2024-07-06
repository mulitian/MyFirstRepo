package com.example.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.domain.pojo.Hospital;
import com.example.domain.pojo.Setmeal;
import com.example.domain.pojo.Users;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * ClassName: OrderVo
 * Package: com.example.domain.vo
 * Description:
 *
 * @Author: Mr.zechaowei
 * @Create: 2024/6/20 - 16:21
 * @Version: v1.0
 */
@Data
public class OrderVo {
    private Integer orderid;
    private String userid;
    private String realname;
    private Integer sex;
    private String hpname;
    //套餐类型
    private String sname;

    private Date orderdate;
    private Integer state;

//    private Integer orderid;
//
//    private Date orderdate;
//
//    private String userid;
//
//    private Integer hpid;
//
//    private Integer smid;
//
//    private Integer state;
//
//    private Users user;
//
//    private Hospital hospital;
//
//    private Setmeal setmeal;


}
