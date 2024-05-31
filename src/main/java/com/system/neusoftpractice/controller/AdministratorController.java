package com.system.neusoftpractice.controller;

import com.system.neusoftpractice.entity.Administrator;
import com.system.neusoftpractice.service.AdministratorService;
import com.system.neusoftpractice.common.*;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/administrator")
@Slf4j
@RequiredArgsConstructor
public class AdministratorController {
    private final AdministratorService administratorService;

    @PostMapping("/login")
    public HttpResponseEntity login(@RequestBody Administrator administrator, HttpServletResponse response) {
        List<Administrator> administratorList = administratorService.query()
                .eq("username", administrator.getUsername())
                .eq("password", administrator.getPassword())
                .eq("status", '1')
                .list();
        if(administratorList.isEmpty())
            return HttpResponseEntity.response(false, "用户名或密码错误", null);
        else{
            Administrator loginAdministrator = administratorList.get(0);
            Cookie cookie = new Cookie("administratorId", loginAdministrator.getId());
            cookie.setSecure(true);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            return HttpResponseEntity.response(true, "登录", administratorList);
        }
    }

    @PostMapping("/addAdministrator")
    public HttpResponseEntity addAdministrator(@RequestBody Administrator administrator) {
        boolean success = administratorService.save(administrator);
        return HttpResponseEntity.response(success, "创建", null);
    }

    @PostMapping("/modifyAdministrator")
    public HttpResponseEntity modifyAdministrator(@RequestBody Administrator administrator) {
        boolean success = administratorService.updateById(administrator);
        return HttpResponseEntity.response(success, "修改", null);
    }

    @PostMapping("/deleteAdministrator")
    public HttpResponseEntity deleteAdministratorById(@RequestBody Administrator administrator) {
        boolean success = administratorService.removeById(administrator);
        return HttpResponseEntity.response(success, "删除", null);
    }

    @PostMapping("/queryAdministratorList")
    public HttpResponseEntity queryAdministratorList(@RequestBody Map<String, Object> map) {
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        Page<Administrator> page = new Page<>(pageNum, pageSize);
        administratorService.query().eq("status", "1")
                .like("username", map.get("username")).page(page);
        List<Administrator> administratorList = page.getRecords();
        boolean success = !administratorList.isEmpty();
        return HttpResponseEntity.response(success, "查询", administratorList);
    }    

    @PostMapping("/logout")
    public HttpResponseEntity logout(HttpServletResponse response) {
        return HttpResponseEntity.response(true, "登出", null);
    }
}
