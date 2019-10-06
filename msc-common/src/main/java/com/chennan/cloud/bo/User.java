package com.chennan.cloud.bo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@NoArgsConstructor          // 无参构造器
@Data                       // 编译阶段生成get set方法
@Accessors(chain = true)    // 链式调用
@TableName("SYS_USER")
public class User implements Serializable {

    @TableId
    private Long userId;

    private String name;

    private String userName;

    private String password;

    private String tel;

    private String gender;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @TableField(exist = false)
    private List<Role> roleList;
}
