package com.example.domain.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * ClassName: UserQuery
 * Package: com.example.domain.query
 * Description:
 *
 * @Author: Mr.zechaowei
 * @Create: 2024/6/20 - 16:34
 * @Version: v1.0
 */
@Data
public class UserQuery {
    private String userid;
    private String realname;
    private Integer sex;
    private Integer smid;

    private Date orderdate;

    private Integer state;

    private Integer pageNum;
    private Integer pageSize;

    public UserQuery() {
    }

//    public UserQuery(String userid, String realname, Integer sex, Integer smid, LocalDateTime orderdate, Integer state) {
//        this.userid = userid;
//        this.realname = realname;
//        this.sex = sex;
//        this.smid = smid;
//        this.orderdate = orderdate;
//        this.state = state;
//    }


    public UserQuery(String userid, String realname, Integer sex, Integer smid, Date orderdate, Integer state, Integer pageNum, Integer pageSize) {
        this.userid = userid;
        this.realname = realname;
        this.sex = sex;
        this.smid = smid;
        this.orderdate = orderdate;
        this.state = state;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
}
