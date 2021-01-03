package com.devops.mvc.controller.role.query;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.devops.base.common.Result;
import com.devops.base.utils.BeanConverter;
import com.devops.mvc.dto.UserRoleDto;
import com.role.api.entity.Role;
import com.role.api.query.QueryRoleFeignApi;
import com.role.api.query.QueryUserRoleFeignApi;
import com.user.api.entity.User;
import com.user.api.query.QueryUserFeignApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: liushuai
 * @date: 2020/11/25
 * @description：对接前端页面的controller
 */
@Slf4j
@RestController
public class QueryUserRoleController {

    @Resource
    private QueryUserFeignApi queryUserFeignApi;
    @Resource
    private QueryUserRoleFeignApi queryUserRoleFeignApi;
    @Resource
    private QueryRoleFeignApi queryRoleFeignApi;

    /**
     * 分页查询用户角色信息
     *
     * @param current 当前页
     * @param size    每页显示行数
     * @param name    用户名称
     * @param orderBy 顺序
     * @return Result<IPage < User>>
     */
    @GetMapping(value = "/mvc/user/role/page")
    public Result<Page<UserRoleDto>> selectUserRolePage(@RequestParam("current") Long current, @RequestParam("size") Long size,
                                                        @RequestParam(value = "name", required = false) String name, @RequestParam(value = "orderBy", required = false) String orderBy) {
        Result<Page<User>> pageResult = queryUserFeignApi.selectUserPage(current, size, name, "");
        if (!pageResult.getSuccess()) {
            log.error("调用queryUserFeignApi.selectUserPage接口失败,ErrorMessage={}", pageResult.getErrorMessage());
        }
        Page<User> userPage = pageResult.getObj();
        List<User> userList = userPage.getRecords();
        if (CollectionUtils.isEmpty(userList)) {
            return new Result<>(new Page<>(current, size));
        }
        List<Integer> uids = userList.stream().map(User::getUid).collect(Collectors.toList());
        Result<Map<Integer, List<Integer>>> mapResult = queryUserRoleFeignApi.selectUserRoleIdsByUids(uids);
        if (!mapResult.getSuccess()) {
            log.error("调用queryUserRoleFeignApi.selectUserRoleIdsByUids接口失败,ErrorMessage={}", mapResult.getErrorMessage());
        }
        Map<Integer, List<Integer>> map = mapResult.getObj();
        if (CollectionUtils.isEmpty(map)) {
            return new Result<>(BeanConverter.convertPageData(userPage, UserRoleDto.class));
        }
        List<UserRoleDto> userRoleDtos = new ArrayList<>(userList.size());
        for (User user : userList) {
            UserRoleDto userRoleDto = BeanConverter.convert(user, UserRoleDto.class);
            List<Integer> roleIdList = map.get(user.getUid());
            Result<List<Role>> listResult = queryRoleFeignApi.selectRoleList(roleIdList);
            if (listResult.getSuccess()) {
                userRoleDto.setRoleList(listResult.getObj());
            } else {
                log.error("调用queryRoleFeignApi.selectRoleList接口失败,ErrorMessage={}", mapResult.getErrorMessage());
            }
            userRoleDtos.add(userRoleDto);
        }
        Page<UserRoleDto> page = new Page<>(current, size, userPage.getTotal());
        page.setRecords(userRoleDtos);
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
            log.error("调用queryRoleFeignApi.selectRolePage接口失败,result={}", pageResult);
        }
        return pageResult;
    }


    /**
     * 查询某个用户的角色列表
     *
     * @param uid 用户id
     * @return Result<List < UserRoleDto>>
     */
    @GetMapping(value = "/mvc/user/role/list/{uid}")
    public Result<UserRoleDto> selectRoleByUid(@PathVariable("uid") Integer uid) {
        Result<User> userResult = queryUserFeignApi.selectUserOne(uid, "");
        if (!userResult.getSuccess()) {
            log.error("调用queryUserFeignApi.selectUserOne接口失败,result={}", userResult);
        }
        User user = userResult.getObj();
        if (null == user) {
            log.error("uid={}，该用户不存在", uid);
            return new Result<>();
        }
        Result<Map<Integer, List<Integer>>> mapResult = queryUserRoleFeignApi.selectUserRoleIdsByUids(Collections.singletonList(uid));
        if (!mapResult.getSuccess()) {
            log.error("调用queryUserRoleFeignApi.selectUserRoleIdsByUids接口失败,result={}", mapResult);
            return new Result<>();
        }
        Map<Integer, List<Integer>> map = mapResult.getObj();
        if (CollectionUtils.isEmpty(map)) {
            return new Result<>();
        }
        Result<List<Role>> roleResult = queryRoleFeignApi.selectRoleList(map.get(uid));
        if (!roleResult.getSuccess()) {
            log.error("调用queryRoleFeignApi.selectRoleList接口失败,result={}", mapResult);
            return new Result<>();
        }
        List<Role> roleList = roleResult.getObj();
        if (CollectionUtils.isEmpty(roleList)) {
            return new Result<>();
        }
        UserRoleDto userRoleDto = BeanConverter.convert(user, UserRoleDto.class);
        userRoleDto.setRoleList(roleList);
        return new Result<>(userRoleDto);
    }

    /**
     * 查询某个用户的角色列表
     *
     * @param uid 用户id
     * @return Result<List < UserRoleDto>>
     */
    @GetMapping(value = "/mvc/user/no/role/list/{uid}")
    public Result<UserRoleDto> selectNoRoleByUid(@PathVariable("uid") Integer uid) {
        Result<User> userResult = queryUserFeignApi.selectUserOne(uid, "");
        if (!userResult.getSuccess()) {
            log.error("调用queryUserFeignApi.selectUserOne接口失败,result={}", userResult);
        }
        User user = userResult.getObj();
        if (null == user) {
            log.error("uid={}，该用户不存在", uid);
            return new Result<>();
        }
        Result<Map<Integer, List<Integer>>> mapResult = queryUserRoleFeignApi.selectUserRoleIdsByUids(Collections.singletonList(uid));
        if (!mapResult.getSuccess()) {
            log.error("调用queryUserRoleFeignApi.selectUserRoleIdsByUids接口失败,result={}", mapResult);
            return new Result<>();
        }
        Map<Integer, List<Integer>> map = mapResult.getObj();
        if (CollectionUtils.isEmpty(map)) {
            Result<List<Role>> allRoleResult = queryRoleFeignApi.selectRoleList();
            if (!allRoleResult.getSuccess()) {
                log.error("调用queryRoleFeignApi.selectRoleList接口失败,result={}", allRoleResult);
                return new Result<>();
            }
            List<Role> allRoleList = allRoleResult.getObj();
            UserRoleDto userRoleDto = BeanConverter.convert(user, UserRoleDto.class);
            userRoleDto.setRoleList(allRoleList);
            return new Result<>(userRoleDto);
        }
        Result<List<Role>> roleResult = queryRoleFeignApi.selectRoleList(map.get(uid));
        if (!roleResult.getSuccess()) {
            log.error("调用queryRoleFeignApi.selectRoleList接口失败,result={}", mapResult);
            return new Result<>();
        }
        List<Role> roleList = roleResult.getObj();
        if (CollectionUtils.isEmpty(roleList)) {
            return new Result<>();
        }

        Result<List<Role>> allRoleResult = queryRoleFeignApi.selectRoleList();
        if (!allRoleResult.getSuccess()) {
            log.error("调用queryRoleFeignApi.selectRoleList接口失败,result={}", allRoleResult);
            return new Result<>();
        }
        List<Role> allRoleList = allRoleResult.getObj();
        if (CollectionUtils.isEmpty(allRoleList)) {
            log.error("角色表数据为空!");
            return new Result<>();
        }
        List<Role> noUserRoleList = allRoleList.stream().filter(o -> !roleList.contains(o)).distinct().collect(Collectors.toList());

        UserRoleDto userRoleDto = BeanConverter.convert(user, UserRoleDto.class);
        userRoleDto.setRoleList(noUserRoleList);
        return new Result<>(userRoleDto);
    }
}
