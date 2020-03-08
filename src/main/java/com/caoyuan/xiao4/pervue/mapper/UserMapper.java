package com.caoyuan.xiao4.pervue.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.caoyuan.xiao4.pervue.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caoyuan.xiao4.pervue.vo.UserVO;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author caoyuan
 * @since 2020-03-03
 */
public interface UserMapper extends BaseMapper<User> {

    Page<UserVO> pageVO(Page<User> userPage, UserVO vo);
}
