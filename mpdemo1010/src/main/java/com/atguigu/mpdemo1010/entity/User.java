package com.atguigu.mpdemo1010.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @author : youminglan
 * @date : 2021/6/14 21:01
 * Create in Wuhan Hubei
 */

@Data
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String email;

    //create time
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    //update time
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;//版本号

    @TableLogic
    private Integer deleted;
}
