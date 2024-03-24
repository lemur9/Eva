package com.lemur.eva.core.exception;

import com.lemur.eva.core.result.DataResult;
import com.lemur.eva.core.utils.HttpContextUtils;
import com.lemur.eva.core.utils.IpUtils;
import com.lemur.eva.core.utils.JsonUtils;
import com.lemur.eva.modules.core.log.pojo.LogError;
import com.lemur.eva.modules.core.log.service.LogService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.UUID;


/**
 * 异常处理器
 *
 */
@RestControllerAdvice
public class EvaExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(EvaExceptionHandler.class);

    @Resource
    private LogService LogService;

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(EvaException.class)
    public DataResult<?> handleEvaException(EvaException ex) {
        DataResult<Object> rv = new DataResult<>();
        return rv.error(ex.getCode(), ex.getMsg());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public DataResult<?> handleDuplicateKeyException(DuplicateKeyException ex) {
        logger.error(ex.getMessage(), ex);

        DataResult<Object> rv = new DataResult<>();
        return rv.error(ErrorCodeMeta.DB_RECORD_EXISTS);
    }

    @ExceptionHandler(Exception.class)
    public DataResult<?> handleException(Exception ex) {
        logger.error(ex.getMessage(), ex);

        saveLog(ex);

        DataResult<Object> rv = new DataResult<>();
        return rv.error();
    }

    /**
     * 保存异常日志
     */
    private void saveLog(Exception ex) {
        LogError log = new LogError();

        //请求相关信息
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();

        if (ObjectUtils.isEmpty(request)) {
            logger.error("The request is empty...");
            return;
        }

        log.setId(UUID.randomUUID().toString());
        log.setIp(IpUtils.getIpAddr(request));
        log.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
        log.setRequestUri(request.getRequestURI());
        log.setRequestMethod(request.getMethod());
        Map<String, String> params = HttpContextUtils.getParameterMap(request);
        if (MapUtils.isNotEmpty(params)) {
            log.setRequestParams(JsonUtils.toJsonString(params));
        }

        //异常信息
        log.setErrorInfo(ExceptionUtils.getErrorStackTrace(ex));

        //保存
        LogService.save(log);
    }
}