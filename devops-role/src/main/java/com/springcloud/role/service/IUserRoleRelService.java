package com.springcloud.role.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springcloud.role.entity.UserRoleRel;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author shuai
 * @since 2020-11-30
 */
public interface IUserRoleRelService extends IService<UserRoleRel> {

    /**
     * @param uids 用户id列表
     * @return List<UserRoleRel>
     */
    List<UserRoleRel> selectUserRoleListByUids(@RequestParam("uids") List<Integer> uids);

}
