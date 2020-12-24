package com.springcloud.mvc.controller.role.query;

import com.role.api.entity.Role;
import com.role.api.query.QueryRoleFeignApi;
import common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: liushuai
 * @date: 2020/11/25
 * @description：对接前端页面的controller
 */
@RestController
public class QueryRoleController {
    private static final Logger logger = LoggerFactory.getLogger(QueryRoleController.class);

    @Resource
    private QueryRoleFeignApi queryRoleFeignApi;

    /**
     * 查询全部角色列表
     *
     * @return Result<List < Role>>
     */
    @GetMapping("/mvc/role/all/list")
    public Result<List<Role>> selectRoleList() {
        return queryRoleFeignApi.selectRoleList();
    }
}
