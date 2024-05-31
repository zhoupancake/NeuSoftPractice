package com.system.neusoftpractice.service.Implement;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.neusoftpractice.entity.AirData;
import com.system.neusoftpractice.mapper.AirDataMapper;
import com.system.neusoftpractice.service.AirDataService;
import org.springframework.stereotype.Service;

@Service
public class AirDataServiceImpl extends ServiceImpl<AirDataMapper, AirData> implements AirDataService {
}
