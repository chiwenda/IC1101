package com.ic1101.common.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author ：chiwd
 * @description：分页参数
 * @date ：2022/7/2 20:23
 */
@ApiModel("分页参数")
@Data
public class PageParam implements Serializable {

    private static final Integer PAGE_NO = 1;
    private static final Integer PAGE_SIZE = 10;


    @ApiModelProperty(value = "页码从1开始", required = true, example = "1")
    @NotNull(message = "pageNo not be null")
    @Min(value = 1, message = "the min value of pageNo must be 1")
    private Integer pageNo = PAGE_NO;

    @ApiModelProperty(value = "每页最大条数，最大值为100", required = true, example = "10")
    @NotNull(message = "pageSize not be null")
    @Min(value = 1, message = "the min value of pageSize must be 1")
    @Max(value = 100, message = "the max value of pageSize must be 100")
    private Integer pageSize = PAGE_SIZE;
}
