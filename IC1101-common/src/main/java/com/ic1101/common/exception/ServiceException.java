package com.ic1101.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 业务逻辑异常
 *
 * @author cwd
 * @date 6/10/22 10:37 AM
 */
@EqualsAndHashCode(callSuper = true)
@Data
public final class ServiceException extends RuntimeException {

    /**
     * 业务错误码
     */
    private Integer code;
    /**
     * 错误提示
     */
    private String message;

    /**
     * 空构造方法，避免反序列化问题
     */
    public ServiceException() {
    }


    public ServiceException(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMsg();
    }

    public ServiceException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public ServiceException setCode(Integer code) {
        this.code = code;
        return this;
    }

}
