package com.caoyuan.xiao4.pervue.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caoyuan.xiao4.pervue.entity.User;
import com.caoyuan.xiao4.pervue.entity.UserRole;
import com.caoyuan.xiao4.pervue.mapper.UserMapper;
import com.caoyuan.xiao4.pervue.mapper.UserRoleMapper;
import com.caoyuan.xiao4.pervue.service.IUserService;
import com.caoyuan.xiao4.pervue.vo.Result;
import com.caoyuan.xiao4.pervue.vo.UserVO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author caoyuan
 * @since 2020-03-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public Page<UserVO> pageVO(Page<User> userPage, UserVO vo) {
        return userMapper.pageVO(userPage, vo);
    }

    @Override
    public boolean removeUsers(List<Integer> asList) {
        if (asList != null && asList.size() > 0) {
            //删除用户角色中间表
            for (Integer uid : asList) {
                QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
                wrapper.eq("uid", uid);
                userRoleMapper.delete(wrapper);
            }
        }
        //批量删除用户
        int i = userMapper.deleteBatchIds(asList);
        return i > 0;
    }

    @Override
    public Result login(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", user.getUsername());
        User one = userMapper.selectOne(wrapper);
        if (one != null) {
            //验证密码,数据库的密码是加密的,所以我们比较加密后的就行了
            String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
            if (one.getPassword().equals(password)) {
                //放入redis,这里调用公共方法
                String token = addUserToRedis(one);
                //存入cookie并发出响应,这里因为cookie没法跨域,所以我们返回前台token
                //Cookie cookie = new Cookie("token", token);
                //response.addCookie(cookie);
                HashMap map = new HashMap<>();
                map.put("user",one);
                map.put("token",token);
                return Result.success(map);
            } else {
                return Result.error(1, "密码不正确");
            }
        } else {
            return Result.error(2, "用户不存在");
        }
    }

    /**
     * @Description 生成用户登录信息token, 并储存入redis, 过期时间是1小时
     * @Param [user]
     * @Return java.lang.String
     * @Author Mr.Cao
     * @Date 2020/3/14
     * @Time 20:29
     */
    private String addUserToRedis(User user) {
        user.setPassword(null);//这里不存密码,涉及用户隐私
        String token = user.getUsername() + "-" + UUID.randomUUID().toString();
        redisTemplate.opsForValue().set("SESSION:" + token, user, 3600, TimeUnit.SECONDS);
        return token;
    }
}
