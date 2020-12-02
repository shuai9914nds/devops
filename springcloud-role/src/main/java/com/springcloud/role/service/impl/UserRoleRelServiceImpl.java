package com.springcloud.role.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springcloud.role.entity.UserRoleRel;
import com.springcloud.role.mapper.UserRoleRelMapper;
import com.springcloud.role.service.IUserRoleRelService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author shuai
 * @since 2020-11-30
 */
@Service
public class UserRoleRelServiceImpl extends ServiceImpl<UserRoleRelMapper, UserRoleRel> implements IUserRoleRelService {

    /**
     * 查询用户角色列表
     *
     * @param uids 用户id列表
     * @return List<UserRoleRel>
     */
    @Override
    public List<UserRoleRel> selectUserRoleListByUids(List<Integer> uids) {
        LambdaQueryWrapper<UserRoleRel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(UserRoleRel::getUid, uids);
        return this.baseMapper.selectList(queryWrapper);
    }

}
