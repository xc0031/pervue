package com.caoyuan.xiao4.pervue.controller;

import com.caoyuan.xiao4.pervue.entity.User;
import com.caoyuan.xiao4.pervue.service.IMenuService;
import com.caoyuan.xiao4.pervue.vo.Result;
import org.springframework.data.redis.core.RedisTemplate;
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
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private IMenuService menuService;

    @Resource
    private RedisTemplate redisTemplate;

    @RequestMapping("list")
    public Result list() {
        return Result.success(menuService.listMenus());
    }

    @RequestMapping("listByUserName")
    public Result listByUserName(String token) {
        //判断redis中有没有用户登录信息
        if (redisTemplate.hasKey("SESSION:" + token)) {
            User user = (User) redisTemplate.opsForValue().get("SESSION:" + token);
            return Result.success(menuService.listByUserName(user.getUsername()));
        } else {
            return Result.error(3, "请先执行登录操作");
        }
    }
}
