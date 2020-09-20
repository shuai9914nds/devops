package com.springcloud.provider.menu.client;


import com.springcloud.menu.api.query.QueryMenuFeignApi;
import com.springcloud.menu.dto.MenuDto;
import com.springcloud.provider.menu.service.MenuService;
import common.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: liushuai
 * @date: 2020/9/1
 * @description：
 */
@RestController
@Api(value = "API - QueryMenuFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class QueryMenuFeignClient implements QueryMenuFeignApi {

    @Autowired
    private MenuService menuService;


    @Override
    @RequestMapping(value = "/selectMenuListAll", method = RequestMethod.POST)
    public Result<List<MenuDto>> selectMenuListAll() {
        return menuService.getMenuListAll();
    }
}
