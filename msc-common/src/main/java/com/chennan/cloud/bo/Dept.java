package com.chennan.cloud.bo;


import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 使用lombok插件基于注解的形式开发，便于开发和后期维护。
 * @author chen.nan
 */
@TableName("DEPT")          // 声明告诉mybatisPlus实体类对应的表名
@NoArgsConstructor          // 无参构造器
@Data                       // 编译阶段生成get set方法
@Accessors(chain = true)    // 链式调用
public class Dept implements Serializable {

    @TableId                     // 声明告诉mybatisPlus deptNo属性对应的字段是表DEPT的主键
    private Long    deptNo;      // 主键
    private String  deptName;    // 部门名称
    private String  dbSource;    // 数据库来源，在微服务架构中，每个微服务

}
