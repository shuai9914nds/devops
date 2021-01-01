package com.devops.menu.client.query;

import com.devops.base.common.Result;
import com.devops.menu.service.IMenuService;
import com.menu.api.dto.MenuDto;
import com.role.api.query.QueryRoleFeignApi;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author: liushuai
 * @date: 2020/9/1
 * @description：提供查询菜单接口
 */
@Slf4j
@RestController
@Api(value = "API - QueryPermController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class QueryPermClient {

    @Autowired
    private IMenuService iMenuService;

    private QueryRoleFeignApi queryRoleFeignApi;


    /**
     * 查询全部权限（树结构）
     *
     * @return Result<List < MenuDto>>
     */
    @GetMapping(value = "/perm/all/tree")
    public Result<List<MenuDto>> selectPermListAll() {
        return new Result<>(iMenuService.getMenuListAll());
    }

    /**
     * 根据权限id查询权限列表
     *
     * @param permIds 权限id
     * @return Result<List < MenuDto>>
     */
    @GetMapping(value = "/perm/tree/{permIds}")
    public Result<List<MenuDto>> selectPermListByPermIds(@PathVariable("permIds") List<Integer> permIds) {
        List<MenuDto> menuDtos = iMenuService.selectPermListByPermIds(permIds);
        if (CollectionUtils.isEmpty(menuDtos)) {
            return new Result<>(menuDtos);
        }
        return new Result<>(menuDtos.stream().distinct().collect(Collectors.toList()));
    }



}
