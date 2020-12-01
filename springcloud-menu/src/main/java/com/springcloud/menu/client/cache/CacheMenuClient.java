package com.springcloud.menu.client.cache;

import com.springcloud.menu.entity.Menu;
import com.springcloud.menu.service.IMenuService;
import common.Result;
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
    public void refreshMenuCache() {
        iMenuService.refreshMenuCache();
    }

    /**
     * 查询缓存中的菜单数据（非树形结构）
     *
     * @return
     */
    @GetMapping("/cache/menu/list")
    public Result<List<Menu>> getMenusByCache() {
        return new Result<>(iMenuService.getMenusByCache());
    }
}
