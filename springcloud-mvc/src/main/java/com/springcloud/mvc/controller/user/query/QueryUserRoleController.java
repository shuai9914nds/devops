package com.springcloud.mvc.controller.user.query;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.role.api.entity.Role;
import com.role.api.query.QueryRoleFeignApi;
import com.springcloud.mvc.dto.UserRoleRelDto;
import com.springcloud.mvc.service.user.IUserRoleService;
import com.user.api.entity.UserInfo;
import common.BaseQueryDto;
import common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: liushuai
 * @date: 2020/11/25
 * @description：对接前端页面的controller
 */
@RestController
public class QueryUserRoleController {
    private static final Logger logger = LoggerFactory.getLogger(QueryUserRoleController.class);

    @Resource
    private IUserRoleService iUserRoleService;
    @Resource
    private QueryRoleFeignApi queryRoleFeignApi;

    /**
     * 分页查询用户角色信息
     *
     * @param current 当前页
     * @param size    每页显示行数
     * @param name    用户名称
     * @param orderBy 顺序
     * @return Result<IPage < UserInfo>>
     */
    @GetMapping(value = "/mvc/user/role/page")
    public Result<Page<UserRoleRelDto>> selectUserRolePage(@RequestParam("current") Long current, @RequestParam("size") Long size,
                                                           @RequestParam(value = "name", required = false) String name, @RequestParam(value = "orderBy", required = false) String orderBy) {

        BaseQueryDto baseQueryDto = new BaseQueryDto();
        baseQueryDto.setCurrent(current);
        baseQueryDto.setSize(size);
        baseQueryDto.setOrderBy(orderBy);
        UserInfo userInfo = new UserInfo();
        userInfo.setName(name);
        Page<UserRoleRelDto> page = iUserRoleService.selectUserRolePage(baseQueryDto, userInfo);
        return new Result<>(page);

    }

    /**
     * 分页查询角色信息
     *
     * @param current  当前页
     * @param size     每页显示行数
     * @param roleName 角色名称
     * @return Result<IPage < Role>>
     */
    @GetMapping(value = "/mvc/role/page")
    public Result<Page<Role>> selectRolePage(@RequestParam("current") Long current, @RequestParam("size") Long size,
                                             @RequestParam(value = "roleName", required = false) String roleName, @RequestParam(value = "orderBy", required = false) String orderBy) {
        Result<Page<Role>> pageResult = queryRoleFeignApi.selectRolePage(current, size, roleName, orderBy);
        if (!pageResult.getSuccess()) {
            logger.error("调用queryRoleFeignApi.selectRolePage接口失败,result={}", pageResult);
        }
        return pageResult;
    }
}
