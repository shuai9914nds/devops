package com.springcloud.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springcloud.user.entity.UserInfo;
import com.springcloud.user.mapper.UserInfoMapper;
import com.springcloud.user.service.IUserInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author shuai
 * @since 2020-11-24
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    /**
     * 查询一个用户信息
     *
     * @param userInfo 查询条件
     * @return UserInfo
     */
    @Override
    public UserInfo getOneByCondition(UserInfo userInfo) {
        return this.baseMapper.selectOne(getLambdaQueryWrapper(userInfo));
    }

    /**
     * 分页查询用户信息
     *
     * @param page     分页对象
     * @param userInfo userInfo对象
     * @return IPage<UserInfo>
     */
    @Override
    public Page<UserInfo> selectUserPage(Page<UserInfo> page, UserInfo userInfo) {
        return this.baseMapper.selectPage(page, getLambdaQueryWrapper(userInfo));
    }

    /**
     * 获取LambdaQueryWrapper<UserInfo>
     *
     * @param userInfo 用户信息
     * @return LambdaQueryWrapper<UserInfo>
     */
    private LambdaQueryWrapper<UserInfo> getLambdaQueryWrapper(UserInfo userInfo) {
        LambdaQueryWrapper<UserInfo> queryMapper = new LambdaQueryWrapper<>();
        if (null == userInfo) {
            return null;
        }
        if (null != userInfo.getUid()) {
            queryMapper.eq(UserInfo::getUid, userInfo.getUid());
        }
        if (StringUtils.isNotBlank(userInfo.getName())) {
            queryMapper.eq(UserInfo::getName, userInfo.getName());
        }
        if (StringUtils.isNotBlank(userInfo.getUsername())) {
            queryMapper.eq(UserInfo::getUsername, userInfo.getUsername());
        }
        if (StringUtils.isNotBlank(userInfo.getIdCardNum())) {
            queryMapper.eq(UserInfo::getIdCardNum, userInfo.getIdCardNum());
        }
        if (null != userInfo.getState()) {
            queryMapper.eq(UserInfo::getState, userInfo.getState());
        }
        if (StringUtils.isNotBlank(userInfo.getCreateBy())) {
            queryMapper.eq(UserInfo::getCreateBy, userInfo.getCreateBy());
        }
        if (StringUtils.isNotBlank(userInfo.getUpdateBy())) {
            queryMapper.eq(UserInfo::getUpdateBy, userInfo.getUpdateBy());
        }
        return queryMapper;
    }
}
