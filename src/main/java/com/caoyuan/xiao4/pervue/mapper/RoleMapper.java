package com.caoyuan.xiao4.pervue.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.caoyuan.xiao4.pervue.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caoyuan.xiao4.pervue.vo.RoleVO;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author caoyuan
 * @since 2020-03-03
 */
public interface RoleMapper extends BaseMapper<Role> {

    Page<RoleVO> selectVO(Page<RoleVO> roleVOPage, RoleVO vo);
}
