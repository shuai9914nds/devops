package com.devops.menu.client.cache;

import com.devops.api.entity.Menu;
import com.devops.base.common.Result;
import com.devops.menu.service.IMenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: liushuai
 * @date: 2020/11/14
 * @description：
 */
@RestController
public class CacheMenuClient {

    @Resource
    private IMenuService iMenuService;

    /**
     * 刷新菜单缓存
     */
    @GetMapping("/refresh/cache/menu")
    public Result<Void> refreshMenuCache() {
        iMenuService.refreshPermCache();
        return new Result<>();
    }

    /**
     * 查询缓存中的菜单数据（非树形结构）
     *
     * @return Result<List < Menu>>
     */
    @GetMapping("/cache/menu/list")
    public Result<List<Menu>> selectMenusByCache() {
        return new Result<>(iMenuService.getPermsByCache());
    }
}
