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
    //订单id
    private Integer orderid;
    //用户id
    private String userid;
    //用户姓名
    private String realname;
    //性别
    private Integer sex;
    //医院
    private String hpname;
    //套餐类型
    private String sname;
    //预约时间
    private Date orderdate;
    //预约状态
    private Integer state;
}
