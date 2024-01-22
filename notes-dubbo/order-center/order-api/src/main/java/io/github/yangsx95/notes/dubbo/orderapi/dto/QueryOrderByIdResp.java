package io.github.yangsx95.notes.dubbo.orderapi.dto;

import lombok.Getter;
import lombok.Setter;
import io.github.yangsx95.notes.dubbo.orderapi.dto.base.BaseReq;

/**
 * @author Feahters
 * @date 2019/3/20
 */
@Setter
@Getter
public class QueryOrderByIdResp extends BaseReq {

    private long id;
    private String username;
    private String productCode;
    private String productName;
    private double money;
    private long num;
    private String createTime;
}
