package com.caoyuan.xiao4.pervue.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caoyuan.xiao4.pervue.entity.User;
import com.caoyuan.xiao4.pervue.entity.UserRole;
import com.caoyuan.xiao4.pervue.mapper.UserMapper;
import com.caoyuan.xiao4.pervue.mapper.UserRoleMapper;
import com.caoyuan.xiao4.pervue.service.IUserService;
import com.caoyuan.xiao4.pervue.vo.UserVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public Page<UserVO> pageVO(Page<User> userPage, UserVO vo) {
        return userMapper.pageVO(userPage, vo);
    }

    @Override
    public boolean removeUsers(List<Integer> asList) {
        if (asList != null && asList.size() > 0) {
            //删除用户角色中间表
            for (Integer uid : asList) {
                QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
                wrapper.eq("uid", uid);
                userRoleMapper.delete(wrapper);
            }
        }
        //批量删除用户
        int i = userMapper.deleteBatchIds(asList);
        return i > 0;
    }
}
