package com.caoyuan.xiao4.pervue.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.caoyuan.xiao4.pervue.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.caoyuan.xiao4.pervue.vo.RoleVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caoyuan
 * @since 2020-03-03
 */
public interface IRoleService extends IService<Role> {

    Page<RoleVO> selectVO(Page<RoleVO> roleVOPage, RoleVO vo);

    boolean deleteRoles(Integer[] ids);
}
