package com.system.neusoftpractice.service.Implement;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.neusoftpractice.dto.User;
import com.system.neusoftpractice.mapper.UserMapper;
import com.system.neusoftpractice.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
