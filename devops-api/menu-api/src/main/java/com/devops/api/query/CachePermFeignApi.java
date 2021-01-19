package com.devops.api.query;

import com.devops.api.entity.Menu;
import com.devops.base.common.Result;
import io.swagger.annotations.Api;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author: liushuai
 * @date: 2020/8/31
 * @description：提供缓存中权限相关的接口
 */
@Api(value = "API - CachePermFeignApi", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@FeignClient(name = "menu", path = "/menu", contextId = "cachePerm")
public interface CachePermFeignApi {

    /**
     * 查询缓存中的菜单数据（非树形结构）
     *
     * @return Result<List < Menu>>
     */
    @GetMapping("/cache/menu/list")
    Result<List<Menu>> selectMenusByCache();


}
