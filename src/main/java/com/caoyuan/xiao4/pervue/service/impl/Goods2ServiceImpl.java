package com.caoyuan.xiao4.pervue.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caoyuan.xiao4.pervue.entity.Goods2;
import com.caoyuan.xiao4.pervue.entity.Gp;
import com.caoyuan.xiao4.pervue.mapper.Goods2Mapper;
import com.caoyuan.xiao4.pervue.mapper.GpMapper;
import com.caoyuan.xiao4.pervue.service.IGoods2Service;
import com.caoyuan.xiao4.pervue.vo.Goods2VO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author caoyuan
 * @since 2020-03-13
 */
@Service
public class Goods2ServiceImpl extends ServiceImpl<Goods2Mapper, Goods2> implements IGoods2Service {

    @Resource
    private Goods2Mapper goods2Mapper;

    @Resource
    private GpMapper gpMapper;

    @Override
    public Page<Goods2VO> pageVO(Page<Goods2VO> voPage, Goods2VO vo) {
        return goods2Mapper.pageVO(voPage, vo);
    }

    @Override
    public boolean removeGoods(List<Integer> asList) {
        if (asList != null && asList.size() > 0) {
            //删除用户角色中间表
            for (Integer gid : asList) {
                QueryWrapper<Gp> wrapper = new QueryWrapper<>();
                wrapper.eq("gid", gid);
                gpMapper.delete(wrapper);
            }
        }
        //批量删除用户
        int i = goods2Mapper.deleteBatchIds(asList);
        return i > 0;
    }

    @Override
    public boolean saveOrUpdateGoods(Goods2VO vo) {
        Goods2 goods2 = new Goods2();
        goods2.setId(vo.getId());
        goods2.setName(vo.getName());
        goods2.setPic(vo.getPic());
        goods2.setPrice(vo.getPrice());
        goods2.setProvince(vo.getProvince());
        goods2.setCity(vo.getCity());
        goods2.setDistrict(vo.getDistrict());
        if (vo.getId() != null && vo.getId() != 0) {//修改
            QueryWrapper<Gp> wrapper = new QueryWrapper<>();
            wrapper.eq("gid", vo.getId());
            gpMapper.delete(wrapper);
            goods2Mapper.updateById(goods2);
        } else {
            goods2Mapper.insert(goods2);
        }
        String pids = vo.getPids();
        int i = 0;
        if (pids != null && pids.length() > 0) {
            String[] split = pids.split(",");
            for (String s : split) {
                Gp gp = new Gp();
                gp.setGid(goods2.getId());
                gp.setPid(Integer.parseInt(s));
                i = gpMapper.insert(gp);
            }
        }
        return i > 0;
    }
}
