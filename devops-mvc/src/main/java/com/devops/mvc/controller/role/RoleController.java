package com.devops.mvc.controller.role;

import com.devops.api.PermRoleFeignApi;
import com.devops.api.RoleFeignApi;
import com.devops.api.UserRoleFeignApi;
import com.devops.api.dto.MenuRoleDto;
import com.devops.base.annotation.MyLog;
import com.devops.base.common.Constant;
import com.devops.base.common.ErrorCode;
import com.devops.base.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Optional;

/**
 * @author: liushuai
 * @date: 2021/1/3
 * @description：
 */
@Slf4j
@RestController
public class RoleController {

    @Resource
    private UserRoleFeignApi userRoleFeignApi;
    @Resource
    private RoleFeignApi roleFeignApi;
    @Resource
    private PermRoleFeignApi permRoleFeignApi;


    /**
     * 修改角色
     *
     * @param menuRoleDto 菜单角色dto
     * @return Result<Void>
     */
    @PostMapping("/mvc/role")
    public Result<Void> updateMenuRole(@RequestBody MenuRoleDto menuRoleDto) {
        if (null == menuRoleDto || null == menuRoleDto.getRoleId() || StringUtils.isBlank(menuRoleDto.getRoleName())) {
            return new Result<>(ErrorCode.PARAM_ERROR);
        }
        return permRoleFeignApi.updateMenuRole(menuRoleDto);
    }

    /**
     * 删除角色
     *
     * @param map 入参
     * @return Result<Void>
     */
    @DeleteMapping(value = "/mvc/role")
    @MyLog(operation = "删除角色", type = Constant.DELETE_ROLE)
    public Result<Void> deleteRole(@RequestBody Map<String, String> map) {
        Integer uid = Optional.ofNullable(map.get("uid")).map(Integer::new).orElse(null);
        Integer roleId = Optional.ofNullable(map.get("roleId")).map(Integer::new).orElse(null);
        //删除用户角色关联关系
        Result<Void> userRoleResult = userRoleFeignApi.deleteUserRole(roleId, uid);
        if (null == roleId) {
            return userRoleResult;
        }
        return roleFeignApi.deleteRole(roleId);
    }

    /**
     * 新增角色
     *
     * @param menuRoleDto 菜单角色dto
     * @return Result<Void>
     */
    @MyLog(operation = "新增角色", type = Constant.ADD_ROLE)
    @PutMapping("/mvc/role")
    public Result<Void> addMenuRole(@RequestBody MenuRoleDto menuRoleDto) {
        if (null == menuRoleDto || StringUtils.isBlank(menuRoleDto.getRoleName()) || CollectionUtils.isEmpty(menuRoleDto.getMenuIds())) {
            return new Result<>(ErrorCode.PARAM_ERROR);
        }
        return permRoleFeignApi.addMenuRole(menuRoleDto);
    }


}
