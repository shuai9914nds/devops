package com.devops.menu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.menu.api.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author shuai
 * @since 2020-12-01
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

}
