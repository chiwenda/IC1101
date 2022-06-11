package com.ic1101.common.util.servlet;

import cn.hutool.core.io.IoUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.ic1101.common.util.json.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.imageio.IIOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 客户端工具
 *
 * @author cwd
 * @date 6/10/22 5:37 PM
 */
@Slf4j
public class ServletUtils {

    /**
     * 返回json
     */
    public static void writeJson(HttpServletResponse response, Object object) {
        String content = JsonUtils.toJsonString(object);
        ServletUtil.write(response, content, MediaType.APPLICATION_JSON_UTF8_VALUE);
    }


    public static void writeAttachment(HttpServletResponse response, String filename, byte[] content) {
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" +
                    URLEncoder.encode(filename, "UTF-8"));
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            //输出附件
            IoUtil.write(response.getOutputStream(), true, content);
        } catch (UnsupportedEncodingException e) {
            log.error("encode attachment error,{}", e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            log.error("output attachment file error,{}", e);
            throw new RuntimeException(e);
        }
    }

    public static String getUserAgent(HttpServletRequest request) {
        String ua = request.getHeader("User-Agent");
        return ua == null ? "" : ua;
    }

    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (!(requestAttributes instanceof ServletRequestAttributes)) {
            return null;
        }

        return ((ServletRequestAttributes) requestAttributes).getRequest();
    }
}
