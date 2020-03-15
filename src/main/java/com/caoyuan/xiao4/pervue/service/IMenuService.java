package com.caoyuan.xiao4.pervue.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.caoyuan.xiao4.pervue.entity.Menu;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author caoyuan
 * @since 2020-03-03
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 查询所有
     *
     * @return
     */
    List<Menu> listMenus();

    List<Menu> listByUserName(String userName);
}
