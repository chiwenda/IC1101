package com.common.tools.modules.redis.receiver;

import cn.hutool.core.util.ObjectUtil;
import com.common.tools.base.BaseMap;
import com.common.tools.constant.GlobalConstants;
import com.common.tools.modules.redis.config.RedisListener;
import com.common.tools.utils.SpringContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author cwd
 * @date 2022/5/17 下午3:56
 */
@Component

public class RedisReceiver {

    public void onMessage(BaseMap params) {
        Object handlerName = params.get(GlobalConstants.HANDLER_NAME);
        RedisListener messageListener = SpringContextHolder.getHanlder(handlerName.toString(), RedisListener.class);
        if (ObjectUtil.isNotEmpty(messageListener)) {
            messageListener.onMessage(params);
        }
    }
}
