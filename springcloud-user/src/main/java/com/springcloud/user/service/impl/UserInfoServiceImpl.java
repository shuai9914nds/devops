package com.springcloud.user.service.impl;

import com.springcloud.user.entity.UserInfo;
import com.springcloud.user.mapper.UserInfoMapper;
import com.springcloud.user.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shuai
 * @since 2020-10-02
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
