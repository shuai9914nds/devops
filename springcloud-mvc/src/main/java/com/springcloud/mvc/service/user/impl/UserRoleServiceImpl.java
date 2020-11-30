package com.springcloud.mvc.service.user.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.role.api.entity.Role;
import com.role.api.query.QueryRoleFeignApi;
import com.springcloud.mvc.dto.UserRoleRelDto;
import com.springcloud.mvc.service.user.IUserRoleService;
import com.user.api.entity.UserInfo;
import com.user.api.query.QueryUserFeignApi;
import com.user.api.query.QueryUserRoleFeignApi;
import common.BaseQueryDto;
import common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import utils.BeanConverter;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: liushuai
 * @date: 2020/11/30
 * @description：用户角色相关service
 */
@Service
public class UserRoleServiceImpl implements IUserRoleService {

    private static final Logger logger = LoggerFactory.getLogger(UserRoleServiceImpl.class);


    @Resource
    private QueryUserFeignApi queryUserFeignApi;
    @Resource
    private QueryUserRoleFeignApi queryUserRoleFeignApi;
    @Resource
    private QueryRoleFeignApi queryRoleFeignApi;

    /**
     * 分页查询用户角色列表
     *
     * @param baseQueryDto 查询dto
     * @param userInfo     用户信息
     * @return 用户角色实体类分页列表
     */
    @Override
    public Page<UserRoleRelDto> selectUserRolePage(BaseQueryDto baseQueryDto, UserInfo userInfo) {
        Result<Page<UserInfo>> userResult = queryUserFeignApi.selectUserPage(baseQueryDto.getCurrent(), baseQueryDto.getSize(), userInfo.getName(), baseQueryDto.getOrderBy());
        if (!userResult.getSuccess()) {
            logger.info("调用接口QueryUserFeignApi.selectUserPage失败，返回结果为{}", userResult);
            return new Page<>(0, 0);
        }
        Page<UserInfo> userInfoPage = userResult.getObj();
        List<UserInfo> users = userInfoPage.getRecords();
        if (CollectionUtils.isEmpty(users)) {
            logger.info("查询用户信息为空");
            return new Page<>(0, 0);
        }
        List<Integer> uids = users.stream().map(UserInfo::getUid).collect(Collectors.toList());
        //根据用户id查询角色id列表
        Result<Map<Integer, List<Integer>>> uidResult = queryUserRoleFeignApi.selectUserRoleIdsByUids(uids);
        if (!uidResult.getSuccess()) {
            logger.error("调用queryUserRoleFeignApi.selectUserRoleListByUids接口失败,result={}", userResult);
            return new Page<>(0, 0);
        }
        //key为uid，value为该用户的角色id列表
        Map<Integer, List<Integer>> uidMap = uidResult.getObj();
        Page<UserRoleRelDto> userRoleRelDtoPage = BeanConverter.convertPageData(userInfoPage, UserRoleRelDto.class);
        if (CollectionUtils.isEmpty(uidMap)) {
            return userRoleRelDtoPage;
        }
        List<Integer> roleIds = new ArrayList<>();
        //获取角色id列表
        for (Integer uid : uids) {
            List<Integer> list = uidMap.get(uid);
            if (!CollectionUtils.isEmpty(list)) {
                roleIds.addAll(list);
            }
        }
        List<Integer> collect = roleIds.stream().distinct().collect(Collectors.toList());
        Result<List<Role>> roleResult = queryRoleFeignApi.selectRoleList(collect);
        if (!roleResult.getSuccess()) {
            return userRoleRelDtoPage;
        }
        List<Role> roles = roleResult.getObj();
        if (CollectionUtils.isEmpty(roles)) {
            return userRoleRelDtoPage;
        }
        //key为roleId，value为role列表
        Map<Integer, List<Role>> roleIdMap = roles.stream().distinct().collect(Collectors.groupingBy(Role::getRoleId));
        List<UserRoleRelDto> records = userRoleRelDtoPage.getRecords();
        for (UserRoleRelDto userRoleRelDto : records) {
            Integer uid = userRoleRelDto.getUid();
            List<Integer> roleIdList = uidMap.get(uid);
            if (!CollectionUtils.isEmpty(roleIdList)) {
                List<Role> roleList = new ArrayList<>();
                for (Integer roleId : roleIdList) {
                    List<Role> roleList1 = roleIdMap.get(roleId);
                    if (!CollectionUtils.isEmpty(roleList1)) {
                        roleList.addAll(roleIdMap.get(roleId));
                    }
                }
                if (!CollectionUtils.isEmpty(roleList)) {
                    List<Role> disRoleList = roleList.stream().distinct().collect(Collectors.toList());
                    userRoleRelDto.setRoleList(disRoleList);
                }
            }
        }
        return userRoleRelDtoPage;
    }
}
