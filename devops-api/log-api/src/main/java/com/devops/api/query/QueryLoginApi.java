package com.devops.api.query;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.devops.api.entity.SysLog;
import com.devops.base.common.Result;
import io.swagger.annotations.Api;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: liushuai
 * @date: 2020/11/25
 * @description：
 */
@Api(value = "API - QueryLogFeignApi", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@FeignClient(name = "log", path = "log", contextId = "queryLog")
public interface QueryLoginApi {

    /**
     * 分页查询系统日志列表
     *
     * @param current 分页参数
     * @param size    分页参数
     * @return Result<Page < SysLog>>
     */
    @GetMapping(value = "/log/page")
    Result<Page<SysLog>> selectSysLogPage(@RequestParam("current") Long current, @RequestParam("size") Long size);
}
