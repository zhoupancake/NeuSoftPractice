package com.system.neusoftpractice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.system.neusoftpractice.entity.Task;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TaskMapper extends BaseMapper<Task> {
}
