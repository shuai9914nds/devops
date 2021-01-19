package com.devops.menu.client.query;

import com.devops.api.dto.MenuDto;
import com.devops.api.entity.Menu;
import com.devops.api.query.QueryPermRoleFeignApi;
import com.devops.base.common.ErrorCode;
import com.devops.base.common.Result;
import com.devops.menu.service.IMenuService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
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

    @Resource
    private IMenuService iMenuService;
    @Resource
    private QueryPermRoleFeignApi queryPermRoleFeignApi;


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
    public Result<List<MenuDto>> selectPermTreeByPermIds(@PathVariable("permIds") List<Integer> permIds) {
        List<MenuDto> menuDtos = iMenuService.selectPermTreeByPermIds(permIds);
        if (CollectionUtils.isEmpty(menuDtos)) {
            return new Result<>(menuDtos);
        }
        return new Result<>(menuDtos.stream().distinct().collect(Collectors.toList()));
    }

    /**
     * 查询某个用户的权限列表
     *
     * @param uid 用户id
     * @return Result<List < Menu>>
     */
    @GetMapping(value = "/perm/list/{uid}")
    public Result<List<Menu>> selectPermListByUid(@PathVariable("uid") Integer uid) {
        Result<List<Integer>> result = queryPermRoleFeignApi.selectPermByUid(uid);
        if (!result.getSuccess()) {
            log.error("调用queryPermRoleFeignApi.selectPermByUid接口失败,result为:{}", result.getErrorMessage());
            return new Result<>(ErrorCode.SYSTEM_ERROR);
        }
        List<Integer> permIdList = result.getObj();
        if (CollectionUtils.isEmpty(permIdList)) {
            return new Result<>(Collections.emptyList());
        }
        List<Menu> menus = iMenuService.selectPermListByPermIds(permIdList);
        return new Result<>(menus);
    }


}
