package com.sentinel.login.loginsoa.service;


import com.sentinel.login.loginsoa.dao.mapper.UserInfoMapper;
import com.sentinel.login.loginsoa.model.UserInfo;
import com.sentinel.login.loginsoa.model.UserInfoExample;
import common.ErrorCode;
import common.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import utils.MD5Utils;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 刘帅
 * @since 2020-05-17
 */
@Service
public class UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 查询用户信息核对数据
     *
     * @param userInfo
     * @return
     * @throws Exception
     */
    public Result checkUserInfo(UserInfo userInfo) throws Exception {
        UserInfoExample userInfoExample = new UserInfoExample();
        UserInfoExample.Criteria criteria = userInfoExample.createCriteria();
        if (StringUtils.isNotEmpty(userInfo.getUname())) {
            criteria.andUnameEqualTo(userInfo.getUname());
        }
        List<UserInfo> userInfos = userInfoMapper.selectByExample(userInfoExample);
        //如果userInfos为空，说明用户名或者密码错误
        if (CollectionUtils.isEmpty(userInfos)) {
            return new Result(ErrorCode.UNAME_OR_PASSWORD_ERROR);
        }
        if (userInfos.size() != 1) {
            return new Result(ErrorCode.SYSTEM_ERROR);
        }
        UserInfo userInfoDB = userInfos.get(0);
        if (!userInfoDB.getUname().equals(userInfo.getUname()) || !userInfoDB.getPassword().equals(MD5Utils.md5(userInfo.getPassword()))) {
            return new Result(ErrorCode.UNAME_OR_PASSWORD_ERROR);
        }
        return new Result(true);
    }
}
