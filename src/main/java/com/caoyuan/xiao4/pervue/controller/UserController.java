package com.caoyuan.xiao4.pervue.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.caoyuan.xiao4.pervue.entity.User;
import com.caoyuan.xiao4.pervue.service.IUserService;
import com.caoyuan.xiao4.pervue.vo.Result;
import com.caoyuan.xiao4.pervue.vo.UserVO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService iUserService;

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * @Description 列表分页查询
     * @Param [current, size]
     * @Return com.caoyuan.xiao4.pervue.vo.Result
     * @Author Mr.Cao
     * @Date 2020/3/3
     * @Time 19:26
     */
    @RequestMapping("list")
    public Result list(@RequestParam(defaultValue = "1") Integer current, UserVO vo, @RequestParam(defaultValue = "3") Integer size) {
        Page<User> userPage = new Page<>(current, size);
        Page<UserVO> page = iUserService.pageVO(userPage, vo);
        return Result.success(page);
    }

    /**
     * @Description 单删除和批删除, 删除中间表
     * @Param [ids]
     * @Return com.caoyuan.xiao4.pervue.vo.Result
     * @Author Mr.Cao
     * @Date 2020/3/3
     * @Time 19:35
     */
    @RequestMapping("delUser")
    public Result delUser(Integer[] ids) {
        return Result.success(iUserService.removeUsers(Arrays.asList(ids)));
    }

    /**
     * @Description 添加方法
     * @Param [user]
     * @Return com.caoyuan.xiao4.pervue.vo.Result
     * @Author Mr.Cao
     * @Date 2020/3/4
     * @Time 12:45
     */
    @RequestMapping("saveOrUpdateUser")

    public Result addUser(@RequestBody User user) {
        return Result.success(iUserService.saveOrUpdate(user));
    }

    @RequestMapping("login")
    public Result login( User user) {
        return iUserService.login(user);
    }

    //退出登录, 根据前台传回来的cookie中的token进行删除
    @RequestMapping("logout")
    public Result logout(String token) {
        return Result.success(redisTemplate.delete("SESSION:" + token));
    }

    //原始的遍历cookies,进行查找cookie
    //@RequestMapping("logout")
    //public Result logout(HttpServletRequest request) {
    //    String token = "";
    //    Cookie[] cookies = request.getCookies();
    //    for (Cookie cookie : cookies) {
    //        if ("token".equals(cookie.getName())) {
    //            token = cookie.getValue();
    //        }
    //    }
    //    return Result.success(redisTemplate.delete("SESSION:" + token));
    //}

    //使用注解, 直接找到cookie, 不用前端传值,无法解决cookie跨域问题
    //@RequestMapping("logout")
    //public Result logout(@CookieValue("token") String token) {
    //    System.err.println(token);
    //    return Result.success(redisTemplate.delete("SESSION:" + token));
    //}
}
