package com.caoyuan.xiao4.pervue.vo;

import com.caoyuan.xiao4.pervue.entity.User;
import lombok.Data;

/*********************************************************
 @author 曹原
 @date 2020/3/5 10:21 
 *********************************************************/
@Data
public class UserVO extends User {
    //省
    private String provinceName;

    //市
    private String cityName;

    //区
    private String districtName;

    //扩展角色表的属性
    private String rids;
    private String rnames;
    private String rnameZhs;
}
