package com.system.neusoftpractice.service.Implement;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.neusoftpractice.entity.Supervisor;
import com.system.neusoftpractice.mapper.SupervisorMapper;
import com.system.neusoftpractice.service.SupervisorService;
import org.springframework.stereotype.Service;

@Service
public class SupervisorServiceImpl extends ServiceImpl<SupervisorMapper, Supervisor> implements SupervisorService{
}
