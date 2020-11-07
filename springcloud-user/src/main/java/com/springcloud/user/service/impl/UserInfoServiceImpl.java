package com.springcloud.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.springcloud.user.entity.UserInfo;
import com.springcloud.user.enumc.UserStateEnum;
import com.springcloud.user.mapper.UserInfoMapper;
import com.springcloud.user.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author shuai
 * @since 2020-10-02
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    @Override
    public UserInfo getOneByCondition(UserInfo userInfo) {
//        return this.baseMapper.selectOne(getLambdaQueryWrapper(userInfo));
        return null;
    }

    /**
     * 获取LambdaQueryWrapper<UserInfo>
     *
     * @param userInfo 用户信息
     * @return
     */
//    private LambdaQueryWrapper<UserInfo> getLambdaQueryWrapper(UserInfo userInfo) {
//        LambdaQueryWrapper<UserInfo> queryMapper = new LambdaQueryWrapper<>();
//        if (null == userInfo) {
//            return null;
//        }
//        if (null != userInfo.getUid()) {
//            queryMapper.eq(UserInfo::getUid, userInfo.getUid());
//        }
//        if (StringUtils.isNotBlank(userInfo.getName())) {
//            queryMapper.eq(UserInfo::getName, userInfo.getName());
//        }
//        if (StringUtils.isNotBlank(userInfo.getUsername())) {
//            queryMapper.eq(UserInfo::getUsername, userInfo.getUsername());
//        }
//        if (StringUtils.isNotBlank(userInfo.getIdCardNum())) {
//            queryMapper.eq(UserInfo::getIdCardNum, userInfo.getIdCardNum());
//        }
//        if (null == userInfo.getState()) {
//            queryMapper.eq(UserInfo::getState, UserStateEnum.NORMAL.getCode());
//        } else {
//            queryMapper.eq(UserInfo::getState, userInfo.getState());
//        }
//        if (StringUtils.isNotBlank(userInfo.getCreateBy())) {
//            queryMapper.eq(UserInfo::getCreateBy, userInfo.getCreateBy());
//        }
//        if (StringUtils.isNotBlank(userInfo.getUpdateBy())) {
//            queryMapper.eq(UserInfo::getUpdateBy, userInfo.getUpdateBy());
//        }
//        return queryMapper;
//    }
}
