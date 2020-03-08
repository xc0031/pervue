package com.caoyuan.xiao4.pervue.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.caoyuan.xiao4.pervue.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.caoyuan.xiao4.pervue.vo.UserVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caoyuan
 * @since 2020-03-03
 */
public interface IUserService extends IService<User> {

    Page<UserVO> pageVO(Page<User> userPage, UserVO vo);

    boolean removeUsers(List<Integer> asList);
}
