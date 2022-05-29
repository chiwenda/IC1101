package com.biz.system.modules.system.controller;

import cn.hutool.core.util.RandomUtil;
import com.biz.system.modules.system.util.RandImageUtil;
import com.common.core.common.api.vo.Result;
import com.common.core.common.util.Md5Util;
import com.common.tools.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author cwd
 * @date 2022/5/17 下午4:25
 */
@RestController
@RequestMapping("/sys")
@Api(tags = "用户登录")
@Slf4j
public class LoginController {

    @Autowired
    private RedisUtil redisUtil;


    /**
     * 生成图形二维码
     *
     * @param response response
     * @param key      key
     */
    @ApiOperation("获取验证码")
    @GetMapping(value = "/randomImage/{key}")
    public Result<String> randomImage(HttpServletResponse response, @PathVariable("key") String key) {
        Result<String> result = new Result<>();
        try {
            //生成验证码
            final String BASE_CHECK_CODES = "qwertyuiplkjhgfdsazxcvbnmQWERTYUPLKJHGFDSAZXCVBNM1234567890";
            String randomString = RandomUtil.randomString(BASE_CHECK_CODES, 4);
            //保存到redis
            final String code = randomString.toUpperCase();
            String realKey = Md5Util.md5Encode(code + key, "utf-8");
            log.info("获取验证码，Redis checkCode = {}，key = {}", randomString, key);
            redisUtil.set(realKey, code, 60);

            String base64 = RandImageUtil.generate(code);
            result.setSuccess(true);
            result.setResult(base64);
        } catch (Exception e) {
            result.error500("获取验证码出错" + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}
