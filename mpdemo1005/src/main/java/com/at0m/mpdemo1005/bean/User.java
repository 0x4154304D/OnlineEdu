package com.at0m.mpdemo1005.bean;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.util.Date;

/**
 * @author 7l2
 */
@Data
public class User {
//    @TableId(type = IdType.ID_WORKER) //mp自带策略，生成19位值，数字类型使用这种策略，比如long
//    @TableId(type = IdType.ID_WORKER_STR) //mp自带策略，生成19位值，自UC还类型那个使用这种策略
//    @TableId(type = IdType.NONE)
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
    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;
    @TableLogic
    private Integer deleted;
}
