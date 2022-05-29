package com.common.tools.modules.redis.config;

import com.common.tools.base.BaseMap;

/**
 * 自定义消息监听
 *
 * @author cwd
 * @date 2022/5/17 下午3:45
 */
public interface RedisListener {
    /**
     * 接收消息
     *
     * @param message 消息
     */
    void onMessage(BaseMap message);
}
