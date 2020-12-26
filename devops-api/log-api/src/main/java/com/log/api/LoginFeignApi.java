package com.log.api;

import com.devops.base.common.Result;
import com.log.api.entity.SysLog;
import io.swagger.annotations.Api;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author: liushuai
 * @date: 2020/11/25
 * @description：
 */
@Api(value = "API - LogFeignApi", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@FeignClient(name = "log-server", path = "log", contextId = "log")
public interface LoginFeignApi {

    /**
     * 新增系统日志
     *
     * @param sysLog 实体类对象
     * @return Result<Void>
     */
    @PutMapping(value = "/log")
    Result<Void> insertSysLog(@RequestBody SysLog sysLog);
}
