package com.system.neusoftpractice.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("user_info")
public class User {
    @TableId
    private String id;
    private String username;
    private String password;
    private Integer status;
    private String role;
}
