package com.hang.up.util.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by 顺 on 2018/8/12.
 */

public class SessionFilter extends OncePerRequestFilter {

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(
     * javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 不过滤的uri
        String[] notFilter = new String[] {"index.html" };

        // 请求的uri
        String uri = request.getRequestURI();

        // uri中包含manager时才进行过滤

        // 是否过滤
        boolean doFilter = true;
        for (String s : notFilter) {
            if (uri.indexOf(s) != -1) {
                // 如果uri中包含不过滤的uri，则不进行过滤
                doFilter = false;
                break;
            }
        }
        if (doFilter) {
            // 执行过滤
            // 从session中获取登录者实体
            Object obj = request.getSession().getAttribute("loginUser");
            if (null == obj) {
                // 如果session中不存在登录者实体，则弹出框提示重新登录
                // 设置request和response的字符集，防止乱码
                response.setContentType("application/json; charset=utf-8");
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                out.print("{\n" +
                        "\"errCode\":\"600\",\n" +
                        "\"url\":\"/mblrLogin\"\n" +
                        "}");
            } else {
                // 如果session中存在登录者实体，则继续
                filterChain.doFilter(request, response);
            }
        } else {
            // 如果不执行过滤，则继续
            filterChain.doFilter(request, response);
        }
    }
}
