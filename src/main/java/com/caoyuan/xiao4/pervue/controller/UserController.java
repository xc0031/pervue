package com.caoyuan.xiao4.pervue.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.caoyuan.xiao4.pervue.entity.User;
import com.caoyuan.xiao4.pervue.service.IUserService;
import com.caoyuan.xiao4.pervue.vo.Result;
import com.caoyuan.xiao4.pervue.vo.UserVO;
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
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService iUserService;

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
        boolean save = iUserService.saveOrUpdate(user);
        return Result.success(save);
    }
}
