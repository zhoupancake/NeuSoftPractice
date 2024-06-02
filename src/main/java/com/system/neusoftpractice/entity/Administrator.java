package com.system.neusoftpractice.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("administrator_info")
public class Administrator implements Serializable {
    //Account Attributes
    @TableId
    private String id;

    //Personal Attributes
    private String idCard;
    private String name;
}
