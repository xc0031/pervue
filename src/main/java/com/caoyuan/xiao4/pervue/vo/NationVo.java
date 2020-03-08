package com.caoyuan.xiao4.pervue.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/*********************************************************
 @author 曹原
 @date 2020/3/4 18:52 
 *********************************************************/
@Data
public class NationVo implements Serializable {
    //对应三级联动的id值 id ，cid,did
    private Integer value;

    //对应三级联动的名称：province，city ,district
    private String label;

    //三级联动的子代元素
    private List<NationVo> children;

}
