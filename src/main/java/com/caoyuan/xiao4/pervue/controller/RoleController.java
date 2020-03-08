package com.caoyuan.xiao4.pervue.controller;

import com.caoyuan.xiao4.pervue.service.IRoleService;
import com.caoyuan.xiao4.pervue.vo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

    /**
     * @Description 全查角色, 用于添加的时候复选框显示
     * @Param []
     * @Return com.caoyuan.xiao4.pervue.vo.Result
     * @Author Mr.Cao
     * @Date 2020/3/5
     * @Time 18:37
     */
    @RequestMapping("list")
    public Result list() {
        return Result.success(iRoleService.list());
    }
}
