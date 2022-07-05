package com.ic1101.base.web.log.core.filter;

import com.ic1101.base.web.web.core.filter.ApiRequestFilter;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：chiwd
 * @description：api日志访问过滤器
 * @date ：2022/7/3 14:45
 */
@Slf4j
public class ApiLogFilter extends ApiRequestFilter {

    private final String applicationName;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    }
}
