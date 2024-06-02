package com.system.neusoftpractice.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("report_info")
public class Report {
    @TableId
    private String id;
    private String location;
    private Integer status;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;
    private String description;
    private String imageUrl;
}
