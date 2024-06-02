package com.system.neusoftpractice.service.Implement;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.neusoftpractice.entity.Task;
import com.system.neusoftpractice.mapper.TaskMapper;
import com.system.neusoftpractice.service.TaskService;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {
}
