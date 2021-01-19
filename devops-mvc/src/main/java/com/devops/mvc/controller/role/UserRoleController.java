package com.devops.mvc.controller.role;

import com.devops.api.RoleFeignApi;
import com.devops.api.UserRoleFeignApi;
import com.devops.base.annotation.MyLog;
import com.devops.base.common.Constant;
import com.devops.base.common.Result;
import com.devops.mvc.dto.AddUserRoleRelDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Optional;

/**
 * @author: liushuai
 * @date: 2020/11/25
 * @description：对接前端页面的controller
 */
@RestController
public class UserRoleController {
    private static final Logger logger = LoggerFactory.getLogger(UserRoleController.class);

    @Resource
    private UserRoleFeignApi userRoleFeignApi;
    @Resource
    private RoleFeignApi roleFeignApi;

    /**
     * 为用户添加角色
     *
     * @param addUserRoleRelDto 关联关系对象
     * @return Result<Void>
     */
    @MyLog(operation = "新增用户角色", type = Constant.ADD_USER_ROLE)
    @PutMapping("/mvc/user/role")
    public Result<Void> addUserRole(@RequestBody AddUserRoleRelDto addUserRoleRelDto) {
        return userRoleFeignApi.addUserRole(addUserRoleRelDto.getRoleId(), addUserRoleRelDto.getUid());
    }

    /**
     * 删除用户一个角色
     *
     * @param map 请求对象
     * @return Result<Void>
     */
    @MyLog(operation = "删除用户角色", type = Constant.DELETE_USER_ROLE)
    @Transactional(rollbackFor = Exception.class)
    @DeleteMapping(value = "/mvc/user/role")
    public Result<Void> deleteUserRole(@RequestBody Map<String, String> map) {

        Integer uid = Optional.ofNullable(map.get("uid")).map(Integer::new).orElse(null);
        Integer roleId = Optional.ofNullable(map.get("roleId")).map(Integer::new).orElse(null);
        //删除用户角色关联关系
        return userRoleFeignApi.deleteUserRole(roleId, uid);
    }
}
