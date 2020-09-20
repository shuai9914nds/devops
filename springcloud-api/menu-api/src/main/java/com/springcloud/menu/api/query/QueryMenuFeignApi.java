package com.springcloud.menu.api.query;

import com.springcloud.menu.dto.MenuDto;
import common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author: liushuai
 * @date: 2020/8/31
 * @description：
 */
@Api(value = "API - QueryMenuFeignApi", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@FeignClient(name = "menu-server", path = "/menu")
public interface QueryMenuFeignApi {

    /**
     * 查询全部用户信息列表
     *
     * @return
     */
    @ApiOperation(httpMethod = "GET", value = "查询全部菜单列表")
    @RequestMapping(value = "/selectMenuListAll", method = RequestMethod.GET)
    Result<List<MenuDto>> selectMenuListAll();
}
