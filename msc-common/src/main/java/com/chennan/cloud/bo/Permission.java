package com.chennan.cloud.bo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor          // 无参构造器
@Data                       // 编译阶段生成get set方法
@Accessors(chain = true)    // 链式调用
@TableName("SYS_PERMISSION")
public class Permission implements Serializable {
    @TableId
    private Long permissionId;
    private String method;
    private String zuulPrefix;
    private String servicePrefix;
    private String uri;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
