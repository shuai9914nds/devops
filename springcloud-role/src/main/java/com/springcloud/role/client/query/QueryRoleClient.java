package com.springcloud.role.client.query;


import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springcloud.role.entity.Role;
import com.springcloud.role.service.IRoleService;
import common.Result;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

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

}