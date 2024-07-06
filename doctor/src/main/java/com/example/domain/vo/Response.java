package com.example.domain.vo;

import lombok.Data;

/**
 * ClassName: Response
 * Package: com.example.domain.vo
 * Description:
 *
 * @Author: Mr.zechaowei
 * @Create: 2024/6/20 - 15:10
 * @Version: v1.0
 */
@Data
public class Response <T>{
    private Integer code;
    private String msg;
    private T result;

    public Response(Integer code, String msg, T result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }
}
