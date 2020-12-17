package com.springcloud.log.client;

import com.springcloud.log.entity.SysLog;
import com.springcloud.log.service.ISysLogService;
import common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: liushuai
 * @date: 2020/12/16
 * @description：系统日志client
 */
@RestController
public class SysLogClient {

    private static final Logger logger = LoggerFactory.getLogger(SysLogClient.class);

    @Resource
    private ISysLogService iSysLogService;

    /**
     * 新增系统日志
     *
     * @param sysLog 实体类对象
     * @return Result<Void>
     */
    @PutMapping(value = "/log")
    public Result<Void> insertSysLog(@RequestBody SysLog sysLog) {
        return new Result<>(iSysLogService.save(sysLog));
    }
}
