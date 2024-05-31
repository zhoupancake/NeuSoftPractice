package com.system.neusoftpractice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.neusoftpractice.common.HttpResponseEntity;
import com.system.neusoftpractice.entity.GridManager;
import com.system.neusoftpractice.service.GridManagerService;
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
@RequestMapping("/gridManager")
@Slf4j
@RequiredArgsConstructor
public class GridManagerController {
    private final GridManagerService gridManagerService;

    @PostMapping("/login")
    public HttpResponseEntity login(@RequestBody GridManager gridManager, HttpServletResponse response) {
        List<GridManager> gridManagerList = gridManagerService.query()
                .eq("username", gridManager.getUsername())
                .eq("password", gridManager.getPassword())
                .eq("status", '1')
                .list();
        if(gridManagerList.isEmpty())
            return HttpResponseEntity.response(false, "用户名或密码错误", null);
        else{
            GridManager loginGridManager = gridManagerList.get(0);
            Cookie cookie = new Cookie("gridManagerId", loginGridManager.getId());
            cookie.setSecure(true);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            return HttpResponseEntity.response(true, "登录", gridManagerList);
        }
    }

    @PostMapping("/addGridManager")
    public HttpResponseEntity addGridManager(@RequestBody GridManager gridManager) {
        boolean success = gridManagerService.save(gridManager);
        return HttpResponseEntity.response(success, "创建", null);
    }

    @PostMapping("/modifyGridManager")
    public HttpResponseEntity modifyGridManager(@RequestBody GridManager gridManager) {
        boolean success = gridManagerService.updateById(gridManager);
        return HttpResponseEntity.response(success, "修改", null);
    }

    @PostMapping("/deleteGridManager")
    public HttpResponseEntity deleteGridManagerById(@RequestBody GridManager gridManager) {
        boolean success = gridManagerService.removeById(gridManager);
        return HttpResponseEntity.response(success, "删除", null);
    }

    @PostMapping("/queryGridManagerList")
    public HttpResponseEntity queryGridManagerList(@RequestBody Map<String, Object> map) {
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        Page<GridManager> page = new Page<>(pageNum, pageSize);
        gridManagerService.query().eq("status", "1")
                .like("username", map.get("username")).page(page);
        List<GridManager> gridManagerList = page.getRecords();
        boolean success = !gridManagerList.isEmpty();
        return HttpResponseEntity.response(success, "查询", gridManagerList);
    }

    @PostMapping("/logout")
    public HttpResponseEntity logout(HttpServletResponse response) {
        return HttpResponseEntity.response(true, "登出", null);
    }
}
