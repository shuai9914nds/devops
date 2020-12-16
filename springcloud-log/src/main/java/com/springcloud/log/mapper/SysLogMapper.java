package com.springcloud.log.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springcloud.log.entity.SysLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author shuai
 * @since 2020-12-16
 */
@Mapper
public interface SysLogMapper extends BaseMapper<SysLog> {

}
