package com.system.neusoftpractice.controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.neusoftpractice.common.HttpResponseEntity;
import com.system.neusoftpractice.entity.*;
import com.system.neusoftpractice.service.*;
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

    @PostMapping("/login")
    public HttpResponseEntity login(@RequestBody Supervisor supervisor, HttpServletResponse response) {
        List<Supervisor> supervisorList = supervisorService.query()
                .eq("username", supervisor.getUsername())
                .eq("password", supervisor.getPassword())
                .eq("status", '1')
                .list();
        if(supervisorList.isEmpty())
            return HttpResponseEntity.response(false, "用户名或密码错误", null);
        else{
            Supervisor loginSupervisor = supervisorList.get(0);
            Cookie cookie = new Cookie("supervisorId", loginSupervisor.getId());
            cookie.setSecure(true);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            return HttpResponseEntity.response(true, "登录", supervisorList);
        }
    }

    @PostMapping("/addSupervisor")
    public HttpResponseEntity addSupervisor(@RequestBody Supervisor supervisor) {
        boolean success = supervisorService.save(supervisor);
        return HttpResponseEntity.response(success, "创建", null);
    }

    @PostMapping("/modifySupervisor")
    public HttpResponseEntity modifySupervisor(@RequestBody Supervisor supervisor) {
        boolean success = supervisorService.updateById(supervisor);
        return HttpResponseEntity.response(success, "修改", null);
    }

    @PostMapping("/deleteSupervisor")
    public HttpResponseEntity deleteSupervisorById(@RequestBody Supervisor supervisor) {
        boolean success = supervisorService.removeById(supervisor);
        return HttpResponseEntity.response(success, "删除", null);
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
