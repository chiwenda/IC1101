package com.ic1101.common.pojo;

import com.ic1101.common.exception.ErrorCode;
import com.ic1101.common.exception.enums.ErrorCodeConstants;
import lombok.Data;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.Objects;

/**
 * 通用返回
 *
 * @author cwd
 * @date 6/10/22 5:01 PM
 */
@Data
public class CommonResult<T> implements Serializable {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;

    public static <T> CommonResult<T> error(Integer code, String msg) {
        Assert.isTrue(!ErrorCodeConstants.SUCCESS.getCode().equals(code), "code必须是错误码");
        CommonResult<T> result = new CommonResult<>();
        result.code = code;
        result.msg = msg;
        return result;
    }

    public static <T> CommonResult<T> error(ErrorCode errorCode) {
        return error(errorCode.getCode(), errorCode.getMsg());
    }

    public static <T> CommonResult<T> error(CommonResult<?> result) {
        return error(result.getCode(), result.getMsg());
    }


    public static <T> CommonResult<T> success(T data) {
        CommonResult<T> result = new CommonResult<>();
        result.code = ErrorCodeConstants.SUCCESS.getCode();
        result.msg = "success";
        result.data = data;
        return result;
    }

    public static boolean isSuccess(Integer code) {
        return Objects.equals(code, ErrorCodeConstants.SUCCESS.getCode());
    }

    public boolean isSuccess() {
        return isSuccess(code);
    }

    public boolean isError() {
        return !isSuccess();
    }


}
