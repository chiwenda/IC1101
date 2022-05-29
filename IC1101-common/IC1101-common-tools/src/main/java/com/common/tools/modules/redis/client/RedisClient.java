package com.common.tools.modules.redis.client;

import com.common.tools.base.BaseMap;
import com.common.tools.constant.GlobalConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * redis 客户端
 *
 * @author cwd
 * @date 2022/5/17 下午3:47
 */
@Configuration
public class RedisClient {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void sendMessage(String handlerName, BaseMap params) {
        params.put(GlobalConstants.HANDLER_NAME, handlerName);
        //send to redis message queue
        redisTemplate.convertAndSend(GlobalConstants.REDIS_TOPIC_NAME, params);
    }
}
