package com.caoyuan.xiao4.pervue.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caoyuan.xiao4.pervue.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author caoyuan
 * @since 2020-03-03
 */
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 查询所有
     * @return
     */
    List<Menu> listMenus();

    List<Menu> listByUserName(@Param("userName") String userName);
}
