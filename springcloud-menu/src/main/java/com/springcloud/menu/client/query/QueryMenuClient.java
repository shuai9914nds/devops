package com.springcloud.menu.client.query;

import com.menu.api.dto.MenuDto;
import com.menu.api.query.QueryMenuFeignApi;
import com.springcloud.menu.service.IMenuService;
import common.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author: liushuai
 * @date: 2020/9/1
 * @description：
 */
@RestController
@Api(value = "API - QueryMenuController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class QueryMenuClient implements QueryMenuFeignApi {

    @Autowired
    private IMenuService iMenuService;


    @Override
    @GetMapping(value = "/menus")
    public Result<List<MenuDto>> selectMenuListAll() {
        return new Result<>(iMenuService.getMenuListAll());
    }
}
