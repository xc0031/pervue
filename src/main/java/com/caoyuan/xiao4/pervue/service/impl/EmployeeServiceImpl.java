package com.caoyuan.xiao4.pervue.service.impl;

import com.caoyuan.xiao4.pervue.entity.Employee;
import com.caoyuan.xiao4.pervue.mapper.EmployeeMapper;
import com.caoyuan.xiao4.pervue.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author caoyuan
 * @since 2020-03-03
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

}
