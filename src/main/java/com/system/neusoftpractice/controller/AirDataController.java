package com.system.neusoftpractice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.neusoftpractice.common.HttpResponseEntity;
import com.system.neusoftpractice.entity.AirData;
import com.system.neusoftpractice.service.AirDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/airData")
@Slf4j
@RequiredArgsConstructor
public class AirDataController {
    private final AirDataService airDataService;
    @PostMapping("/addAirData")
    public HttpResponseEntity addAirData(@RequestBody AirData airData) {
        boolean success = airDataService.save(airData);
        return HttpResponseEntity.response(success, "创建", null);
    }

    @PostMapping("/modifyAirData")
    public HttpResponseEntity modifyAirData(@RequestBody AirData airData) {
        boolean success = airDataService.updateById(airData);
        return HttpResponseEntity.response(success, "修改", null);
    }

    @PostMapping("/deleteAirData")
    public HttpResponseEntity deleteAirDataById(@RequestBody AirData airData) {
        boolean success = airDataService.removeById(airData);
        return HttpResponseEntity.response(success, "删除", null);
    }

    @PostMapping("/queryAirDataList")
    public HttpResponseEntity queryAirDataList(@RequestBody Map<String, Object> map) {
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        Page<AirData> page = new Page<>(pageNum, pageSize);
        airDataService.query().eq("status", "1")
                .like("username", map.get("username")).page(page);
        List<AirData> airDataList = page.getRecords();
        boolean success = !airDataList.isEmpty();
        return HttpResponseEntity.response(success, "查询", airDataList);
    }
}
