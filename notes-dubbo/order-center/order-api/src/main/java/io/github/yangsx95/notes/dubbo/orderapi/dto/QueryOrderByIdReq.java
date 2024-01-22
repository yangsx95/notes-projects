package io.github.yangsx95.notes.dubbo.orderapi.dto;

import lombok.Data;
import io.github.yangsx95.notes.dubbo.orderapi.dto.base.BaseReq;

/**
 * @author Feahters
 * @date 2019/3/20
 */
@Data
public class QueryOrderByIdReq extends BaseReq {
    private long orderId;
}
