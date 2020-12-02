package com.springcloud.mvc.controller.menu;

import com.menu.api.dto.MenuDto;
import com.menu.api.query.QueryMenuFeignApi;
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
 * @description：菜单相关的controller
 */
@RestController
@Api(value = "API - QueryMenuController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RoleController implements QueryMenuFeignApi {

    @Autowired
    private QueryMenuFeignApi queryMenuFeignApi;


    @Override
    @GetMapping(value = "/mvc/menu/all/tree")
    public Result<List<MenuDto>> selectMenuListAll() {
        return queryMenuFeignApi.selectMenuListAll();
    }
}
