package com.ic1101.common.exception;

import lombok.Data;

/**
 * 错误码对象
 *
 * @author cwd
 * @date 6/10/22 10:30 AM
 */
@Data
public class ErrorCode {

    /**
     * 错误码
     */
    private final Integer code;


    /**
     * 错误提示
     */
    private final String msg;

    public ErrorCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
