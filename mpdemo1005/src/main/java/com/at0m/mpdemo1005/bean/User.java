package com.at0m.mpdemo1005.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @author 7l2
 * ORM编程思想 （Object Relational Mapping）
 * 一个数据表对应一个Java类，表中的一个字段对应Java的一个属性
 */
@Data
public class User {
//    @TableId(type = IdType.ID_WORKER) //mp自带策略，生成19位值，数字类型使用这种策略，比如long
//    @TableId(type = IdType.ID_WORKER_STR) //mp自带策略，生成19位值，字符类型使用这种策略
//    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String email;
    //create_time
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //update_time
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    //测试乐观锁
    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;
    //逻辑删除
    @TableLogic
    private Integer deleted;
}
