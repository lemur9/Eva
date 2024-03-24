package com.lemur.eva.core.filter.web;

import com.lemur.eva.core.result.DataResultMeta;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class WebVisitFilter implements Filter {

    // 授信列表
    private final List<String> trustList = new ArrayList<>();

    // 授信路径列表
    private final List<String> trustLikeList = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) {
        String contextPath = filterConfig.getServletContext().getContextPath();

        trustList.add(contextPath + "/api/login");

        trustLikeList.add(contextPath + "/eva/security/access");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        // 业务处理
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        resp.addHeader("Access-Control-Allow-Origin", "http://127.0.0.1:");
        resp.addHeader("Access-Control-Request-Method", "POST,GET");

        if (this.doAccessFilter(req)) {
            try{
                chain.doFilter(request, response);
            }catch (Exception e) {
                log.error("Filter execution failed", e);
            }
        } else {
            this.accessTimeout(req, resp);
        }
    }

    private boolean doAccessFilter(HttpServletRequest request) {
        //需要先做doPass 否则应用发布获取票据的请求会被拦截
        if (this.doPass(request)) {
            return true;
        }

        //校验url权限
//        if (!this.checkUriPermission(request)) {
//            return false;
//        }

//        return signatureUserService.getSignatureUser(request) != null;
        return true;
    }

    private boolean doPass(HttpServletRequest request) {
        String uri = request.getRequestURI();

        for (String trust : this.trustList) {
            if (trust.equals(uri)) {
                log.info("The request is verified through type: trusted URL list");
                return true;
            }
        }

        for (String trust : this.trustLikeList) {
            if (uri.contains(trust)) {
                log.info("The request path is verified through type: trusted URL list");
                return true;
            }
        }

        return false;
    }

    public void accessTimeout(HttpServletRequest request, HttpServletResponse response) {
        String protocol =  request.getScheme();
        String host = request.getServerName();
        int port = request.getLocalPort();

        String url = protocol + "://" + host + ":" + port + request.getServletContext().getContextPath() + "/api/login";

        if("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))) {
            response.setHeader(DataResultMeta.STR_REDIRECT_HEADER, String.valueOf(DataResultMeta.WAIT_TIMEOUT));
            response.setHeader(DataResultMeta.STR_REDIRECT_PATH_HEADER, url);
            response.setStatus(HttpServletResponse.SC_REQUEST_TIMEOUT);
        }

        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
    }
}
