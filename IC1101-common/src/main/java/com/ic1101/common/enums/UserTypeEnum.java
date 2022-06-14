package com.ic1101.common.enums;

import lombok.Getter;

/**
 * 用户类型枚举
 *
 * @author cwd
 * @date 6/13/22 9:49 AM
 */
@Getter
public enum UserTypeEnum {

    MEMBER(1, "会员"),//普通用户

    ADMIN(2, "管理员");//管理员


    private final Integer value;

    private final String name;

    UserTypeEnum(Integer value, String name) {
        this.name = name;
        this.value = value;
    }

}
