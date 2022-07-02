package com.ic1101.common.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author ：chiwd
 * @description：排序字段
 * @date ：2022/7/2 20:43
 */
@Data
@Accessors(chain = true)
public class SortingField implements Serializable {

    public static final String ORDER_ASC = "asc";

    public static final String ORDER_DESC = "desc";

    //字段
    private String field;

    //顺序
    private String order;

    //反序列化需要无参构造函数
    public SortingField() {
    }

    public SortingField(String field, String order) {
        this.field = field;
        this.order = order;
    }
}
