package com.springcloud.mvc.controller.menu;

import com.menu.api.MenuRoleFeignApi;
import com.menu.api.dto.MenuRoleDto;
import common.ErrorCode;
import common.Result;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @author: liushuai
 * @date: 2020/9/1
 * @description：角色菜单相关controller
 */
@RestController
@Api(value = "API - MenuRoleController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MenuRoleController {

    @Resource
    private MenuRoleFeignApi menuRoleFeignApi;

    /**
     * 新增角色菜单关联关系
     *
     * @param menuRoleDto 菜单角色dto
     * @return Result<Void>
     */
    @PutMapping("/mvc/menu/role")
    public Result<Void> addMenuRole(@RequestBody MenuRoleDto menuRoleDto) {
        if (null == menuRoleDto || StringUtils.isBlank(menuRoleDto.getRoleName()) || CollectionUtils.isEmpty(menuRoleDto.getMenuIds())) {
            return new Result<>(ErrorCode.PARAM_ERROR);
        }
        return menuRoleFeignApi.addMenuRole(menuRoleDto);
    }
}
