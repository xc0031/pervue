package com.caoyuan.xiao4.pervue.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caoyuan.xiao4.pervue.entity.Menu;
import com.caoyuan.xiao4.pervue.mapper.MenuMapper;
import com.caoyuan.xiao4.pervue.service.IMenuService;
import com.caoyuan.xiao4.pervue.vo.Result;
import com.mysql.cj.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author caoyuan
 * @since 2020-03-03
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Resource
    private MenuMapper menuMapper;

    @Override
    public List<Menu> listMenus() {
        return menuMapper.listMenus();
    }

    @Override
    public List<Menu> listByUserName(String userName) {
        //判断有没有传过来名字
        if (StringUtils.isNullOrEmpty(userName)) {
            return new ArrayList<Menu>();
        }
        return menuMapper.listByUserName(userName);
    }
}
