package com.caoyuan.xiao4.pervue.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caoyuan.xiao4.pervue.entity.Nation;
import com.caoyuan.xiao4.pervue.vo.NationVo;

import java.util.List;

/**
 * <p>
 * 城市字典表 Mapper 接口
 * </p>
 *
 * @author caoyuan
 * @since 2020-03-03
 */
public interface NationMapper extends BaseMapper<Nation> {

    /**
    * @Description 三级联动,全查
    * @Param       []
    * @Return      java.util.List<com.caoyuan.xiao4.pervue.vo.NationVo>
    * @Author      Mr.Cao
    * @Date        2020/3/4
    * @Time        19:05
    */
    List<NationVo> findAll();
}
