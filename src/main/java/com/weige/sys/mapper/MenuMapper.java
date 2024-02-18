package com.weige.sys.mapper;

import com.weige.sys.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author laocai
 * @since 2023-07-17
 */
public interface MenuMapper extends BaseMapper<Menu> {
        public List<Menu> getMenuListByUserId(@Param("userId") Integer userId, @Param("pid") Integer pid);


}
