package com.sentinel.login.loginsoa.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.MD5Utils;


/**
 * @author: liushuai
 * @date: 2020/5/16
 * @description：登录controller
 */
@RestController
@RequestMapping("login")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    public common.Result stopSupplierCouponBatchDtoById(String param) throws Exception {
//        SupplierCouponBatchDto supplierCouponBatchDto = JSONObject.parseObject(param, SupplierCouponBatchDto.class);
//
//    }

}
