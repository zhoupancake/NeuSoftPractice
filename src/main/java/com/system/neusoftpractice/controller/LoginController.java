package com.system.neusoftpractice.controller;

import com.system.neusoftpractice.common.HttpResponseEntity;
import com.system.neusoftpractice.dto.User;
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

@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;
    private final AdministratorService administratorService;
    private final GridManagerService gridManagerService;
    private final SupervisorService supervisorService;

    @PostMapping("/login")
    public HttpResponseEntity login(@RequestBody User user, HttpServletResponse response) {
        List<User> administratorList = userService.query()
                .eq("username", user.getUsername())
                .eq("password", user.getPassword())
                .eq("status", '1')
                .list();
        if(administratorList.isEmpty())
            return HttpResponseEntity.response(false, "用户名或密码错误", null);
        else{
            User loginUser = administratorList.get(0);
            switch (loginUser.getRole()) {
                case "Administrator" -> {
                    Administrator administrator = administratorService.getById(loginUser.getId());
                    Cookie cookie = new Cookie("administratorId", administrator.getId());
                    cookie.setSecure(true);
                    cookie.setHttpOnly(true);
                    response.addCookie(cookie);
                    return HttpResponseEntity.response(true, "登录", administrator);
                }
                case "GridManager" -> {
                    GridManager gridManager = gridManagerService.getById(loginUser.getId());
                    Cookie cookie = new Cookie("gridManagerId", gridManager.getId());
                    cookie.setSecure(true);
                    cookie.setHttpOnly(true);
                    response.addCookie(cookie);
                    return HttpResponseEntity.response(true, "登录", gridManager);
                }
                case "Supervisor" -> {
                    Supervisor supervisor = supervisorService.getById(loginUser.getId());
                    Cookie cookie = new Cookie("supervisorId", supervisor.getId());
                    cookie.setSecure(true);
                    cookie.setHttpOnly(true);
                    response.addCookie(cookie);
                    return HttpResponseEntity.response(true, "登录", supervisor);
                }
                default -> {
                    return HttpResponseEntity.response(false, "用户角色信息错误，请联系管理员", null);
                }
            }
        }
    }
}
