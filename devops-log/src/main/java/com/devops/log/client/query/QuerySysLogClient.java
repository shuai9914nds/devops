package com.devops.log.client.query;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.devops.base.common.Result;
import com.devops.log.entity.SysLog;
import com.devops.log.service.ISysLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: liushuai
 * @date: 2020/12/16
 * @description：查询系统日志client
 */
@RestController
public class QuerySysLogClient {

    private static final Logger logger = LoggerFactory.getLogger(QuerySysLogClient.class);

    @Resource
    private ISysLogService iSysLogService;

    /**
     * 分页查询系统日志列表
     *
     * @param current 分页参数
     * @param size    分页参数
     * @return Result<Page < SysLog>>
     */
    @GetMapping(value = "/log/page")
    public Result<Page<SysLog>> selectSysLogPage(@RequestParam("current") Long current, @RequestParam("size") Long size) {
        Page<SysLog> page = new Page<>();
        page.setCurrent(current);
        page.setSize(size);
        LambdaQueryWrapper<SysLog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(SysLog::getCreateTime);
        return new Result<>(iSysLogService.page(page, queryWrapper));
    }
}
