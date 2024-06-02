package com.system.neusoftpractice.service.Implement;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.neusoftpractice.entity.Report;
import com.system.neusoftpractice.mapper.ReportMapper;
import com.system.neusoftpractice.service.ReportService;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report> implements ReportService {
}
