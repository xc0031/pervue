package com.caoyuan.xiao4.pervue.controller;

import com.caoyuan.xiao4.pervue.service.IPromotionService;
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
 * @since 2020-03-13
 */
@RestController
@RequestMapping("/promotion")
public class PromotionController {

    @Resource
    private IPromotionService iPromotionService;
    
    /**
    * @Description 促销全查,为了复选框
    * @Param       []
    * @Return      com.caoyuan.xiao4.pervue.vo.Result
    * @Author      Mr.Cao
    * @Date        2020/3/13
    * @Time        21:07
    */
    @RequestMapping("listAll")
    public Result list() {
        return Result.success(iPromotionService.list());
    }
}
