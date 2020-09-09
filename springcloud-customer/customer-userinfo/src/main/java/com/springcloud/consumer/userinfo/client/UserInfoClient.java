package com.springcloud.consumer.userinfo.client;

import com.springcloud.userinfoapi.dto.UserInfoDto;
import common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author: liushuai
 * @date: 2020/9/9
 * @description：
 */
//name 为product项目中application.yml配置文件中的application.name;
//path 为product项目中application.yml配置文件中的context.path;
@FeignClient(name = "userinfo-server", path = "/product")
@Component
public interface UserInfoClient {

    @RequestMapping(value = "/userinfo/getUserInfos", method = RequestMethod.POST)
    Result<List<UserInfoDto>> getUserInfos();
}
