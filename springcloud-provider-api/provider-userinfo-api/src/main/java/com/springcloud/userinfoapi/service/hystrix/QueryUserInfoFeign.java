package com.springcloud.userinfoapi.service.hystrix;

import com.springcloud.userinfoapi.model.UserInfo;
import com.springcloud.userinfoapi.service.QueryUserInfoFeignApi;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: liushuai
 * @date: 2020/8/31
 * @description：
 */
@Component
public class QueryUserInfoFeign implements QueryUserInfoFeignApi {
    @Override
    public List<UserInfo> getUserInfoByCondition(UserInfo userInfo) {
        return null;
    }

    @Override
    public List<UserInfo> getUserInfos() {
        return null;
    }
}
