package com.devops.mvc.controller.menu;

import com.devops.base.common.ErrorCode;
import com.devops.base.common.Result;
import com.role.api.PermRoleFeignApi;
import com.role.api.dto.MenuRoleDto;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
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
public class PermRoleController {

    @Resource
    private PermRoleFeignApi permRoleFeignApi;

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
        return permRoleFeignApi.addMenuRole(menuRoleDto);
    }

    /**
     * 修改角色菜单关联关系
     *
     * @param menuRoleDto 菜单角色dto
     * @return Result<Void>
     */
    @PostMapping("/mvc/menu/role")
    public Result<Void> updateMenuRole(@RequestBody MenuRoleDto menuRoleDto) {
        if (null == menuRoleDto || null == menuRoleDto.getRoleId() || StringUtils.isBlank(menuRoleDto.getRoleName())) {
            return new Result<>(ErrorCode.PARAM_ERROR);
        }
        return permRoleFeignApi.updateMenuRole(menuRoleDto);
    }
}
