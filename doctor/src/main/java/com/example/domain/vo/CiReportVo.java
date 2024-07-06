package com.example.domain.vo;

import com.example.domain.pojo.Cidetailedreport;
import lombok.Data;

import java.util.List;

/**
 * ClassName: CiReportVo
 * Package: com.example.domain.vo
 * Description:
 *
 * @Author: Mr.zechaowei
 * @Create: 2024/6/24 - 11:46
 * @Version: v1.0
 */
@Data
public class CiReportVo {
    private Integer cirid;
    private Integer ciid;
    private Integer orderid;
    private String ciName;
    private List<Cidetailedreport> cidetailedreport;
}
