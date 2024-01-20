package com.yangsx95.demo.enums;

/**
 * ComputerState
 * <p>
 *
 * @author Feathers
 * @date 2018-05-24 14:24
 */
public enum ComputerState implements IEnum {

    OPEN(10),
    CLOSE(11),
    OFF_LINE(12),
    FAULT(200),
    UNKNOWN(255),;

    private final Integer code;

    ComputerState(int code) {
        this.code = code;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
