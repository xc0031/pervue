package com.caoyuan.xiao4.pervue.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.caoyuan.xiao4.pervue.entity.MenuRole;
import com.caoyuan.xiao4.pervue.vo.RoleVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caoyuan
 * @since 2020-03-03
 */
public interface IMenuRoleService extends IService<MenuRole> {

    boolean saveOrUpdateMenus(RoleVO vo);
}
