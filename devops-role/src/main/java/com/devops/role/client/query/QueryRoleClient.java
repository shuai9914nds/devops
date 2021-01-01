package com.devops.role.client.query;


import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.devops.base.common.ErrorCode;
import com.devops.base.common.Result;
import com.devops.role.service.IRoleService;
import com.role.api.entity.Role;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author: liushuai
 * @date: 2020/11/14
 * @description：查询角色接口
 */
@RestController
@Api(value = "API - QueryRoleController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class QueryRoleClient {

    private static final Logger logger = LoggerFactory.getLogger(QueryRoleClient.class);
    @Resource
    private IRoleService iRoleService;
    @Resource
    private QueryUserRoleClient queryUserRoleClient;


    /**
     * 查询角色列表
     *
     * @param roleIds 角色id列表
     * @return Result<List < Role>>
     */
    @GetMapping(value = "/role/list")
    public Result<List<Role>> selectRoleList(@RequestParam("roleIds") List<Integer> roleIds) {
        return new Result<>(iRoleService.listByIds(roleIds));
    }

    /**
     * 分页查询用户信息
     *
     * @param current  当前页
     * @param size     每页显示行数
     * @param roleName 角色名称
     * @return Result<IPage < Role>>
     */
    @GetMapping(value = "/role/page")
    public Result<Page<Role>> selectRolePage(@RequestParam("current") Long current, @RequestParam("size") Long size,
                                             @RequestParam(value = "roleName", required = false) String roleName, @RequestParam(value = "orderBy", required = false) String orderBy) {
        Page<Role> page = new Page<>();
        page.setCurrent(current);
        page.setSize(size);
        OrderItem orderItem = new OrderItem();
        orderItem.setAsc(false);
        orderItem.setColumn(orderBy);
        page.setOrders(Collections.singletonList(orderItem));
        Role role = new Role();
        role.setRoleName(roleName);
        Page<Role> rolePage = iRoleService.selectUserPage(page, role);
        return new Result<>(rolePage);
    }

    /**
     * 查询一个角色
     *
     * @param roleId   角色id
     * @param roleName 角色名称
     * @return Result<Role>
     */
    @GetMapping(value = "/role")
    public Result<Role> selectOne(@RequestParam(value = "roleId", required = false) Integer roleId, @RequestParam(value = "roleName", required = false) String roleName) {
        Role role = new Role();
        role.setRoleName(roleName);
        role.setRoleId(roleId);
        return new Result<>(iRoleService.getOneByCondition(role));
    }

    /**
     * 查询一个用户拥有的角色
     *
     * @param uid 用户id
     * @return Result<Role>
     */
    @GetMapping(value = "/role/list/{uid}")
    public Result<List<Role>> selectRoleListByUid(@PathVariable(value = "uid") Integer uid) {
        //查询用户的角色id列表
        Result<Map<Integer, List<Integer>>> userRoleResult = queryUserRoleClient.selectUserRoleIdsByUids(Collections.singletonList(uid));
        if (!userRoleResult.getSuccess()) {
            logger.error("调用接口queryUserRoleClient.selectUserRoleIdsByUids失败,result={}", userRoleResult);
            return new Result<>(ErrorCode.SYSTEM_ERROR);
        }
        Map<Integer, List<Integer>> map = userRoleResult.getObj();
        if (CollectionUtils.isEmpty(map)) {
            return new Result<>();
        }
        List<Integer> roleList = map.get(uid);
        if (CollectionUtils.isEmpty(roleList)) {
            logger.error("uid={},在user_role_rel表里的角色id为空", uid);
            return new Result<>();
        }
        List<Role> roles = iRoleService.listByIds(roleList);
        return new Result<>(roles);
    }


    /**
     * 查询全部角色列表
     *
     * @return Result<List < Role>>
     */
    @GetMapping("/role/all/list")
    public Result<List<Role>> selectRoleList() {
        return new Result<>(iRoleService.list());
    }
}
