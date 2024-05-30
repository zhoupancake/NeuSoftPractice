package com.system.neusoftpractice.service.Implement;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.neusoftpractice.entity.GridManager;
import com.system.neusoftpractice.mapper.GridManagerMapper;
import com.system.neusoftpractice.service.GridManagerService;
import org.springframework.stereotype.Service;

@Service
public class GridManagerServiceImpl extends ServiceImpl<GridManagerMapper, GridManager> implements GridManagerService {
}
