package com.caoyuan.xiao4.pervue.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.caoyuan.xiao4.pervue.entity.UserRole;
import com.caoyuan.xiao4.pervue.service.IUserRoleService;
import com.caoyuan.xiao4.pervue.vo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author caoyuan
 * @since 2020-03-03
 */
@RestController
@RequestMapping("/userRole")
public class UserRoleController {

    @Resource
    private IUserRoleService iUserRoleService;

    /**
     * @Description 添加中间表, 用户与角色的中间表
     * @Param [uid, rids]
     * @Return com.caoyuan.xiao4.pervue.vo.Result
     * @Author Mr.Cao
     * @Date 2020/3/6
     * @Time 9:49
     */
    @RequestMapping("insert")
    public Result insert(Integer uid,Integer[] rids) {
        //删除原来的用户角色
        QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid);
        boolean remove = iUserRoleService.remove(wrapper);
        //添加入新的中间表
        ArrayList<UserRole> userRoles = new ArrayList<>();
        for (Integer rid : rids) {
            UserRole userRole = new UserRole();
            userRole.setUid(uid);
            userRole.setRid(rid);
            userRoles.add(userRole);
        }
        boolean b = iUserRoleService.saveBatch(userRoles);
        return Result.success(b);
    }
}
