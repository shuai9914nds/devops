package com.devops.log.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.devops.log.mapper.SysLogMapper;
import com.devops.log.service.ISysLogService;
import com.log.api.entity.SysLog;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author shuai
 * @since 2020-12-21
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {

}
