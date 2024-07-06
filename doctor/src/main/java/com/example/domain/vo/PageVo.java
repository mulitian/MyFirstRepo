package com.example.domain.vo;

import lombok.Data;

import java.util.List;

/**
 * ClassName: PageVo
 * Package: com.example.domain.vo
 * Description:
 *
 * @Author: Mr.zechaowei
 * @Create: 2024/6/25 - 17:03
 * @Version: v1.0
 */
@Data
public class PageVo <T>{
    //总条数
    private Long total;
    //页码
    private Long pages;
    //集合
    private List<T> list;
}
