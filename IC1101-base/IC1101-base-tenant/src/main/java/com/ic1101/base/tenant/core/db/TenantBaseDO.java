package com.ic1101.base.tenant.core.db;

import com.ic1101.base.db.mb.mybatis.core.entity.BaseDO;
import lombok.Data;

/**
 * @author ：chiwd
 * @description：扩展
 * @date ：2022/7/5 22:17
 */
@Data
public abstract class TenantBaseDO extends BaseDO {
    /**
     * 多租户编号
     */
    private Long tenantId;
}
