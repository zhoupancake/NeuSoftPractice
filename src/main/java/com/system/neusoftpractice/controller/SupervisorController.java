package com.system.neusoftpractice.controller;

import com.system.neusoftpractice.common.HttpResponseEntity;
import com.system.neusoftpractice.dto.RequestCharacterEntity;
import com.system.neusoftpractice.dto.User;
import com.system.neusoftpractice.entity.*;
import com.system.neusoftpractice.service.*;
import com.system.neusoftpractice.util.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/supervisor")
@Slf4j
@RequiredArgsConstructor
public class SupervisorController {
    private final SupervisorService supervisorService;
    private final UserService userService;
    @PostMapping("/addSupervisor")
    public HttpResponseEntity addSupervisor(@RequestBody RequestCharacterEntity requestCharacterEntity) {
        Supervisor supervisor = requestCharacterEntity.getSupervisor_create();
        User user = requestCharacterEntity.getUser_create();

        supervisor.setId(SnowflakeUtil.genId());
        user.setId(SnowflakeUtil.genId());
        user.setStatus(1);
        user.setRole("Supervisor");

        boolean supervisorSuccess = supervisorService.save(supervisor);
        boolean userSuccess = userService.save(user);

        return HttpResponseEntity.response(supervisorSuccess&&userSuccess, "创建", null);
    }

    @PostMapping("/modifySupervisor")
    public HttpResponseEntity modifySupervisor(@RequestBody RequestCharacterEntity requestCharacterEntity) {
        Supervisor supervisor = requestCharacterEntity.getSupervisor_create();
        User user = requestCharacterEntity.getUser_create();

        boolean supervisorSuccess = supervisorService.updateById(supervisor);
        boolean userSuccess = userService.updateById(user);

        return HttpResponseEntity.response(supervisorSuccess&&userSuccess, "修改", null);
    }

    @PostMapping("/deleteSupervisor")
    public HttpResponseEntity deleteSupervisorById(@RequestBody RequestCharacterEntity requestCharacterEntity) {
        Supervisor supervisor = requestCharacterEntity.getSupervisor_create();
        User user = requestCharacterEntity.getUser_create();

        boolean supervisorSuccess = supervisorService.removeById(supervisor);
        boolean userSuccess = userService.removeById(user);

        return HttpResponseEntity.response(supervisorSuccess&&userSuccess, "删除", null);
    }

    @PostMapping("/querySupervisorList")
    public HttpResponseEntity querySupervisorList(@RequestBody Map<String, Object> map) {
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        Page<Supervisor> page = new Page<>(pageNum, pageSize);
        supervisorService.query().eq("status", "1")
                .like("username", map.get("username")).page(page);
        List<Supervisor> supervisorList = page.getRecords();
        boolean success = !supervisorList.isEmpty();
        return HttpResponseEntity.response(success, "查询", supervisorList);
    }

    @PostMapping("/logout")
    public HttpResponseEntity logout(HttpServletResponse response) {
        return HttpResponseEntity.response(true, "登出", null);
    }
}
