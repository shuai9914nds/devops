package com.login.api;

import com.devops.base.common.Result;
import com.login.api.dto.LoginDto;
import io.swagger.annotations.Api;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @author: liushuai
 * @date: 2020/11/25
 * @description：
 */
@Api(value = "API - QueryMenuFeignApi", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@FeignClient(name = "login", path = "login", contextId = "login")
public interface LoginApi {

    /**
     * 登录controller
     *
     * @param loginDto
     * @return
     */
    @PostMapping(value = "/login")
    Result<Map<String, Object>> login(@RequestBody LoginDto loginDto);
}
