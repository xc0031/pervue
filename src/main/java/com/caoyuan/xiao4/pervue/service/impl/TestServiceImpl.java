package com.caoyuan.xiao4.pervue.service.impl;

import com.caoyuan.xiao4.pervue.entity.Test;
import com.caoyuan.xiao4.pervue.mapper.TestMapper;
import com.caoyuan.xiao4.pervue.service.ITestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author caoyuan
 * @since 2020-03-08
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements ITestService {

}
