package com.devops.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.devops.base.common.Constant;
import com.devops.base.utils.HttpUtil;
import com.devops.user.mapper.UserMapper;
import com.devops.user.service.IUserService;
import com.user.api.entity.User;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    /**
     * 查询一个用户信息
     *
     * @param user 查询条件
     * @return User
     */
    @Override
    public User getOneByCondition(User user) {
        return this.baseMapper.selectOne(getLambdaQueryWrapper(user));
    }

    /**
     * 分页查询用户信息
     *
     * @param page 分页对象
     * @param user userInfo对象
     * @return IPage<User>
     */
    @Override
    public Page<User> selectUserPage(Page<User> page, User user) {
        return this.baseMapper.selectPage(page, getLambdaQueryWrapper(user));
    }

    /**
     * 获取token
     *
     * @return token
     */
    @Override
    public String getToken() {
        return HttpUtil.getRequest().getHeaders(Constant.USER_TOKEN).toString();
    }

    /**
     * 更新用户信息
     *
     * @param user 用户信息
     */
    @Override
    public void updateUserInfo(User user) {
        User where = new User();
        where.setUid(user.getUid());
        LambdaUpdateWrapper<User> lambdaUpdateWrapper = getLambdaUpdateWrapper(where);
        this.baseMapper.update(user, lambdaUpdateWrapper);
    }

    /**
     * 获取LambdaQueryWrapper<User>
     *
     * @param user 用户信息
     * @return LambdaQueryWrapper<User>
     */
    private LambdaQueryWrapper<User> getLambdaQueryWrapper(User user) {
        LambdaQueryWrapper<User> queryMapper = new LambdaQueryWrapper<>();
        if (null == user) {
            return null;
        }
        if (null != user.getUid()) {
            queryMapper.eq(User::getUid, user.getUid());
        }
        if (StringUtils.isNotBlank(user.getName())) {
            queryMapper.like(User::getName, user.getName());
        }
        if (StringUtils.isNotBlank(user.getUsername())) {
            queryMapper.eq(User::getUsername, user.getUsername());
        }
        if (StringUtils.isNotBlank(user.getIdCardNum())) {
            queryMapper.eq(User::getIdCardNum, user.getIdCardNum());
        }
        if (null != user.getState()) {
            queryMapper.eq(User::getState, user.getState());
        }
        if (StringUtils.isNotBlank(user.getCreateBy())) {
            queryMapper.eq(User::getCreateBy, user.getCreateBy());
        }
        if (StringUtils.isNotBlank(user.getUpdateBy())) {
            queryMapper.eq(User::getUpdateBy, user.getUpdateBy());
        }
        return queryMapper;
    }

    /**
     * 获取LambdaUpdateWrapper<User>
     *
     * @param user 用户信息
     * @return LambdaUpdateWrapper<User>
     */
    private LambdaUpdateWrapper<User> getLambdaUpdateWrapper(User user) {
        if (null == user) {
            return null;
        }
        LambdaUpdateWrapper<User> updateMapper = new LambdaUpdateWrapper<>();
        if (null != user.getUid()) {
            updateMapper.eq(User::getUid, user.getUid());
        }
        if (StringUtils.isNotBlank(user.getName())) {
            updateMapper.eq(User::getName, user.getName());
        }
        if (StringUtils.isNotBlank(user.getUsername())) {
            updateMapper.eq(User::getUsername, user.getUsername());
        }
        if (StringUtils.isNotBlank(user.getIdCardNum())) {
            updateMapper.eq(User::getIdCardNum, user.getIdCardNum());
        }
        if (null != user.getState()) {
            updateMapper.eq(User::getState, user.getState());
        }
        if (StringUtils.isNotBlank(user.getCreateBy())) {
            updateMapper.eq(User::getCreateBy, user.getCreateBy());
        }
        if (StringUtils.isNotBlank(user.getUpdateBy())) {
            updateMapper.eq(User::getUpdateBy, user.getUpdateBy());
        }
        return updateMapper;
    }
}
