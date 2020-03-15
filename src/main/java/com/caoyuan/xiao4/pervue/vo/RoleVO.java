package com.caoyuan.xiao4.pervue.vo;

import com.caoyuan.xiao4.pervue.entity.Role;
import lombok.Data;

/*********************************************************
 @author 曹原
 @date 2020/3/9 9:36 
 *********************************************************/
@Data
public class RoleVO extends Role {

    //菜单的主键集合(前台得切割)
    private String mids;
    //菜单的名字集合
    private String mname;
}
