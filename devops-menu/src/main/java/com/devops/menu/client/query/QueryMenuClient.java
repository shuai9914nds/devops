package com.devops.menu.client.query;

import com.devops.api.dto.MenuDto;
import com.devops.base.common.Result;
import com.devops.menu.service.IMenuService;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author: liushuai
 * @date: 2020/9/1
 * @description：提供查询菜单接口
 */
@RestController
@Api(value = "API - QueryMenuController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class QueryMenuClient {

    @Resource
    private IMenuService iMenuService;


    /**
     * 根据菜单ids查询菜单列表
     *
     * @param menuIds 菜单id列表
     * @return Result<List < MenuDto>>
     */
    @GetMapping(value = "/menu/tree")
    public Result<List<MenuDto>> selectMenuListByMenuIds(@RequestParam(value = "menuIds", required = false) List<Integer> menuIds) {
        List<MenuDto> menuDtos = iMenuService.selectMenuListByMenuIds(menuIds);
        if (CollectionUtils.isEmpty(menuDtos)) {
            return new Result<>(menuDtos);
        }
        return new Result<>(menuDtos.stream().distinct().collect(Collectors.toList()));
    }

}
