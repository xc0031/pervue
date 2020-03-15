package com.caoyuan.xiao4.pervue.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.caoyuan.xiao4.pervue.entity.Role;
import com.caoyuan.xiao4.pervue.service.IMenuRoleService;
import com.caoyuan.xiao4.pervue.service.IRoleService;
import com.caoyuan.xiao4.pervue.vo.Result;
import com.caoyuan.xiao4.pervue.vo.RoleVO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author caoyuan
 * @since 2020-03-03
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private IRoleService iRoleService;

    @Resource
    private IMenuRoleService iMenuRoleService;

    /**
     * @Description 全查角色, 用于添加的时候复选框显示
     * @Param []
     * @Return com.caoyuan.xiao4.pervue.vo.Result
     * @Author Mr.Cao
     * @Date 2020/3/5
     * @Time 18:37
     */
    @RequestMapping("list")
    public Result list(RoleVO vo, @RequestParam(defaultValue = "1") Integer current, @RequestParam(defaultValue = "3") Integer size) {
        Page<RoleVO> roleVOPage = new Page<>(current, size);
        Page<RoleVO> page = iRoleService.selectVO(roleVOPage, vo);
        return Result.success(page);
    }

    /**
    * @Description 授权的复选框列表
    * @Param       []
    * @Return      com.caoyuan.xiao4.pervue.vo.Result
    * @Author      Mr.Cao
    * @Date        2020/3/13
    * @Time        13:19
    */
    @RequestMapping("listAll")
    public Result listAll() {
        return Result.success(iRoleService.list());
    }

    /**
     * @Description 添加或者修改
     * @Param []
     * @Return com.caoyuan.xiao4.pervue.vo.Result
     * @Author Mr.Cao
     * @Date 2020/3/10
     * @Time 18:26
     */
    @RequestMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody Role role) {
        return Result.success(iRoleService.saveOrUpdate(role));
    }

    /**
    * @Description 删除角色,得删除2个中间表
    * @Param       [ids]
    * @Return      com.caoyuan.xiao4.pervue.vo.Result
    * @Author      Mr.Cao
    * @Date        2020/3/13
    * @Time        12:12
    */
    @RequestMapping("/delete")
    public Result delete(Integer[] ids) {
        return Result.success(iRoleService.deleteRoles(ids));
    }
}
