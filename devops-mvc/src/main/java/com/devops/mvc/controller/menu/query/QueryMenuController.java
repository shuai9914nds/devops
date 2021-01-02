package com.devops.mvc.controller.menu.query;

import com.devops.base.common.Result;
import com.menu.api.dto.MenuDto;
import com.menu.api.query.QueryMenuFeignApi;
import com.role.api.query.QueryPermRoleFeignApi;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author: liushuai
 * @date: 2020/12/30
 * @description：查询菜单controller
 */
@RestController
@Slf4j
@Api(value = "API - QueryPermController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class QueryMenuController {


    @Resource
    private QueryPermRoleFeignApi queryPermRoleFeignApi;
    @Resource
    private QueryMenuFeignApi queryMenuFeignApi;

    /**
     * 查询用户所拥有的的菜单列表
     *
     * @return Result<List < MenuDto>>
     */
    @GetMapping(value = "/mvc/menu/list/{uid}")
    public Result<List<MenuDto>> selectMenuIdListByUid(@PathVariable("uid") Integer uid) {
        //查询用户拥有的权限id列表
        Result<List<Integer>> permResult = queryPermRoleFeignApi.selectPermByUid(uid);
        if (!permResult.getSuccess()) {
            log.error("queryPermFeignApi.selectPermByUid,result={}", permResult);
            return new Result<>(Collections.emptyList());
        }
        //用户拥有的权限id
        List<Integer> permIdList = permResult.getObj();
//        Joiner.on(",").join(permIdList);
        Result<List<MenuDto>> menuResult = queryMenuFeignApi.selectMenuListByMenuIds(permIdList);
        if (!menuResult.getSuccess()) {
            log.error("queryMenuFeignApi.selectMenuListByMenuIds,errorMessage={}", menuResult.getErrorMessage());
            return new Result<>(Collections.emptyList());
        }
        return menuResult;
    }
}
