package com.caoyuan.xiao4.pervue.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caoyuan.xiao4.pervue.entity.MenuRole;
import com.caoyuan.xiao4.pervue.entity.Role;
import com.caoyuan.xiao4.pervue.mapper.MenuRoleMapper;
import com.caoyuan.xiao4.pervue.mapper.RoleMapper;
import com.caoyuan.xiao4.pervue.service.IMenuRoleService;
import com.caoyuan.xiao4.pervue.vo.RoleVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements IMenuRoleService {

    @Resource
    private MenuRoleMapper menuRoleMapper;

    @Override
    public boolean saveOrUpdateMenus(RoleVO vo) {
        try {
            //删除原先的中间表
            QueryWrapper<MenuRole> wrapper = new QueryWrapper<>();
            wrapper.eq("rid", vo.getId());
            menuRoleMapper.delete(wrapper);
            //添加新的中间表
            String[] split = vo.getMids().split(",");
            for (String s : split) {
                MenuRole menuRole = new MenuRole();
                menuRole.setMid(Integer.parseInt(s));
                menuRole.setRid(vo.getId());
                menuRoleMapper.insert(menuRole);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
