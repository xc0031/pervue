package com.caoyuan.xiao4.pervue.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.caoyuan.xiao4.pervue.entity.Nation;
import com.caoyuan.xiao4.pervue.vo.NationVo;

import java.util.List;

/**
 * <p>
 * 城市字典表 服务类
 * </p>
 *
 * @author caoyuan
 * @since 2020-03-03
 */
public interface INationService extends IService<Nation> {

    List<NationVo> findAll();
}
