package com.springcloud.role.client;


import com.role.api.RoleFeignApi;
import com.role.api.dto.MenuRoleDto;
import com.role.api.entity.Role;
import com.role.api.query.QueryRoleFeignApi;
import com.springcloud.role.entity.MenuRoleRel;
import com.springcloud.role.service.IMenuRoleRelService;
import common.ErrorCode;
import common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: liushuai
 * @date: 2020/11/14
 * @description：
 */
@RestController
public class MenuRoleClient {

    private static final Logger logger = LoggerFactory.getLogger(MenuRoleClient.class);


    @Resource
    private IMenuRoleRelService iMenuRoleRelService;
    @Resource
    private QueryRoleFeignApi queryRoleFeignApi;
    @Resource
    private RoleFeignApi roleFeignApi;

    /**
     * 新增角色菜单关联关系
     *
     * @param menuRoleDto 菜单角色实体类
     * @return Result<Void>
     */
    @PutMapping("/menu/role")
    @Transactional(rollbackFor = Exception.class)
    public Result<Void> addMenuRole(@RequestBody MenuRoleDto menuRoleDto) {

        Role queryRole = new Role();
        queryRole.setRoleName(menuRoleDto.getRoleName());
        Result<Void> errorResult = new Result<>(ErrorCode.SYSTEM_ERROR);
        Result<Role> roleResult = queryRoleFeignApi.selectOne(menuRoleDto.getRoleId(), menuRoleDto.getRoleName());
        if (!roleResult.getSuccess()) {
            logger.error("调用queryRoleFeignApi.selectOne接口失败，result={}", roleResult);
            return errorResult;
        }
        Role resultRole = roleResult.getObj();

        if (null != resultRole) {
            logger.error("角色名称重复");
            return new Result<>(ErrorCode.ROLE_NAME_REPEAT);
        }
        Role addRole = new Role();
        addRole.setRoleName(menuRoleDto.getRoleName());
        Result<Role> result = roleFeignApi.addRole(addRole);
        if (!result.getSuccess()) {
            logger.error("调用roleFeignApi.addRole接口失败，result={}", result);
            throw new RuntimeException("新增角色失败");
        }
        Role role = result.getObj();
        List<MenuRoleRel> menuRoleRels = new ArrayList<>();
        for (Integer menuId : menuRoleDto.getMenuIds()) {
            MenuRoleRel menuRoleRel = new MenuRoleRel();
            menuRoleRel.setMenuId(menuId);
            //TODO 从token中解析，获取用户名称
            menuRoleRel.setCreateBy("shuai");
            menuRoleRel.setRoleId(role.getRoleId());
            menuRoleRels.add(menuRoleRel);
        }
        iMenuRoleRelService.saveBatch(menuRoleRels);
        return new Result<>();
    }
}
