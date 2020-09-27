package com.springcloud.login.controller;

import com.springcloud.menu.api.query.QueryMenuFeignApi;
import com.springcloud.menu.dto.MenuDto;
import common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: liushuai
 * @date: 2020/9/24
 * @description：
 */
@RestController
public class LoginController {


    @Autowired
    private QueryMenuFeignApi queryMenuFeignApi;

    @PostMapping("/getmenu")
    public Result<List<MenuDto>> getmenu(){
        return queryMenuFeignApi.selectMenuListAll();
    }
}
