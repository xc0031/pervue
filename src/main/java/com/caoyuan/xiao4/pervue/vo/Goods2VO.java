package com.caoyuan.xiao4.pervue.vo;

import com.caoyuan.xiao4.pervue.entity.Goods2;
import lombok.Data;

import java.math.BigDecimal;

/*********************************************************
 @author 曹原
 @date 2020/3/13 19:07 
 *********************************************************/
@Data
public class Goods2VO extends Goods2 {
    //价格区间
    private BigDecimal price1;
    private BigDecimal price2;
    //省
    private String provinceName;
    //市
    private String cityName;
    //区
    private String districtName;

    //扩展促销表的属性
    private String pids;
    private String pnames;
}
