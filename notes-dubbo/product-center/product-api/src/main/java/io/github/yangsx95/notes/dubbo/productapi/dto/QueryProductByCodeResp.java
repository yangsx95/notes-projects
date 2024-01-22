package io.github.yangsx95.notes.dubbo.productapi.dto;

import io.github.yangsx95.notes.dubbo.productapi.dto.base.BaseResp;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QueryProductByCodeResp extends BaseResp {

    private long id;
    private String productCode;
    private String productName;
    private double money;
    private long stock;
    private String updateTime;
    private String createTime;

}
