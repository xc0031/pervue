package com.caoyuan.xiao4.pervue.controller;

import com.caoyuan.xiao4.pervue.service.INationService;
import com.caoyuan.xiao4.pervue.vo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 城市字典表 前端控制器
 * </p>
 *
 * @author caoyuan
 * @since 2020-03-03
 */
@RestController
@RequestMapping("/nation")
public class NationController {

    @Resource
    private INationService iNationService;

    /**
    * @Description 三级联动全查
    * @Param       []
    * @Return      com.caoyuan.xiao4.pervue.vo.Result
    * @Author      Mr.Cao
    * @Date        2020/3/4
    * @Time        19:27
    */
    @RequestMapping("findAll")
    public Result findAll() {
        return Result.success(iNationService.findAll());
    }
}
