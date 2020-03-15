package com.caoyuan.xiao4.pervue.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caoyuan.xiao4.pervue.entity.MenuRole;
import com.caoyuan.xiao4.pervue.entity.Role;
import com.caoyuan.xiao4.pervue.entity.UserRole;
import com.caoyuan.xiao4.pervue.mapper.MenuRoleMapper;
import com.caoyuan.xiao4.pervue.mapper.RoleMapper;
import com.caoyuan.xiao4.pervue.mapper.UserRoleMapper;
import com.caoyuan.xiao4.pervue.service.IRoleService;
import com.caoyuan.xiao4.pervue.vo.RoleVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author caoyuan
 * @since 2020-03-03
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private MenuRoleMapper menuRoleMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public Page<RoleVO> selectVO(Page<RoleVO> roleVOPage, RoleVO vo) {
        return roleMapper.selectVO(roleVOPage, vo);
    }

    @Override
    public boolean deleteRoles(Integer[] ids) {
        int i = 0;
        for (Integer id : ids) {
            //先删除角色-用户的
            QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
            wrapper.eq("rid", id);
            userRoleMapper.delete(wrapper);
            //再删除角色-菜单的
            QueryWrapper<MenuRole> wrapper2 = new QueryWrapper<>();
            wrapper2.eq("rid", id);
            menuRoleMapper.delete(wrapper2);
            //再删除角色自己的
            i = roleMapper.deleteById(id);
        }
        return i>0;
    }
}
