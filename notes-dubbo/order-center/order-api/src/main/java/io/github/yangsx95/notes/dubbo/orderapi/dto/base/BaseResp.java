package io.github.yangsx95.notes.dubbo.orderapi.dto.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Feahters
 * @version 1.0
 * @date 2019/3/21
 */
@Setter
@Getter
public class BaseResp implements Serializable {

    private String code;

    private String msg;
    
}
