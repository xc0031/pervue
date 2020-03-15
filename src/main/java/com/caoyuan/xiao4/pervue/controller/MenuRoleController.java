package com.caoyuan.xiao4.pervue.controller;

import com.caoyuan.xiao4.pervue.service.IMenuRoleService;
import com.caoyuan.xiao4.pervue.vo.Result;
import com.caoyuan.xiao4.pervue.vo.RoleVO;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/menuRole")
public class MenuRoleController {

    @Resource
    private IMenuRoleService iMenuRoleService;

    /**
     * @Description 授权, 添加中间表添加主表
     * @Param [vo]
     * @Return com.caoyuan.xiao4.pervue.vo.Result
     * @Author Mr.Cao
     * @Date 2020/3/10
     * @Time 19:35
     */
    @RequestMapping("saveOrUpdateMenus")
    public Result saveOrUpdateMenus(@RequestBody RoleVO vo) {
        return Result.success(iMenuRoleService.saveOrUpdateMenus(vo));
    }
}
