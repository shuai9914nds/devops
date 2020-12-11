package com.springcloud.role.client;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.springcloud.role.entity.UserRoleRel;
import com.springcloud.role.service.IUserRoleRelService;
import common.ErrorCode;
import common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: liushuai
 * @date: 2020/11/14
 * @description：
 */
@RestController
public class UserRoleClient {
    private static final Logger logger = LoggerFactory.getLogger(UserRoleClient.class);

    @Resource
    private IUserRoleRelService iUserRoleRelService;

    /**
     * 根据用户id或者角色id删除关联关系
     *
     * @param roleId 角色id
     * @param uid    用户id
     * @return Result<Void>
     */
    @DeleteMapping("/user/role")
    public Result<Void> deleteUserRole(@RequestParam(value = "roleId", required = false) Integer roleId,
                                       @RequestParam(value = "uid", required = false) Integer uid) {
        if (null == roleId && null == uid) {
            return new Result<>(ErrorCode.PARAM_ERROR);
        }
        LambdaQueryWrapper<UserRoleRel> queryWrapper = new LambdaQueryWrapper<>();
        if (null != roleId) {
            queryWrapper.eq(UserRoleRel::getRoleId, roleId);
        }
        if (null != uid) {
            queryWrapper.eq(UserRoleRel::getUid, uid);
        }
        iUserRoleRelService.remove(queryWrapper);
        return new Result<>();
    }

}
