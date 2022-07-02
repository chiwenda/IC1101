package com.ic1101.base.db.mb.mybatis.core.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ic1101.base.db.mb.mybatis.core.entity.BaseDO;
import com.ic1101.base.web.core.util.WebBaseUtils;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;
import java.util.Objects;

/**
 * 通用参数自动填充实现
 *
 * @author cwd
 * @date 6/14/22 4:56 PM
 */
public class DbFieldHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        if (Objects.nonNull(metaObject) && metaObject.getOriginalObject() instanceof BaseDO) {
            BaseDO baseDO = (BaseDO) metaObject.getOriginalObject();
            Date current = new Date();

            //创建时间为空自动填充当前时间
            if (Objects.isNull(baseDO.getCreateTime())) {
                baseDO.setCreateTime(current);
            }

            //更新时间为空自动填充当前时间
            if (Objects.isNull(baseDO.getUpdateTime())) {
                baseDO.setUpdateTime(current);
            }

            Long userId = WebBaseUtils.getLoginUserId();
            //当前登录用户为创建人和更新人
            if (Objects.nonNull(userId)) {
                if (Objects.isNull(baseDO.getCreator())) {
                    baseDO.setCreator(String.valueOf(userId));
                }
                if (Objects.isNull(baseDO.getUpdater())) {
                    baseDO.setUpdater(String.valueOf(userId));
                }
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {

        Object updateTime = getFieldValByName("updateTime", metaObject);
        if (Objects.isNull(updateTime)) {
            setFieldValByName("updateTime", new Date(), metaObject);
        }

        Object updater = getFieldValByName("updater", metaObject);
        Long userId = WebBaseUtils.getLoginUserId();
        if (Objects.nonNull(userId) && Objects.isNull(updater)) {
            setFieldValByName("updater", String.valueOf(userId), metaObject);
        }
    }
}
