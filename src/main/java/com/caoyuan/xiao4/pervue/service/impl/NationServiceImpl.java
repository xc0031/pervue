package com.caoyuan.xiao4.pervue.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caoyuan.xiao4.pervue.entity.Nation;
import com.caoyuan.xiao4.pervue.mapper.NationMapper;
import com.caoyuan.xiao4.pervue.service.INationService;
import com.caoyuan.xiao4.pervue.vo.NationVo;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 城市字典表 服务实现类
 * </p>
 *
 * @author caoyuan
 * @since 2020-03-03
 */
@Service
public class NationServiceImpl extends ServiceImpl<NationMapper, Nation> implements INationService {

    @Resource
    private NationMapper nationMapper;

    @Cacheable(value = "pervue", key = "'pervue_nationVos'")
    @Override
    public List<NationVo> findAll() {
        System.err.println("第一次会访问数据库,以后都在redis中");
        return nationMapper.findAll();
    }
}
