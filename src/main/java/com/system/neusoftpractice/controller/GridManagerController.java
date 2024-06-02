package com.system.neusoftpractice.controller;

import com.system.neusoftpractice.common.HttpResponseEntity;
import com.system.neusoftpractice.dto.RequestCharacterEntity;
import com.system.neusoftpractice.dto.User;
import com.system.neusoftpractice.entity.Administrator;
import com.system.neusoftpractice.entity.GridManager;
import com.system.neusoftpractice.service.GridManagerService;
import com.system.neusoftpractice.service.UserService;
import com.system.neusoftpractice.util.SnowflakeUtil;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/gridManager")
@Slf4j
@RequiredArgsConstructor
public class GridManagerController {
    private final GridManagerService gridManagerService;
    private final UserService userService;
    @PostMapping("/addGridManager")
    public HttpResponseEntity addGridManager(@RequestBody RequestCharacterEntity requestCharacterEntity) {
        GridManager gridManager = requestCharacterEntity.getGridManager_create();
        User user = requestCharacterEntity.getUser_create();

        gridManager.setId(SnowflakeUtil.genId());
        user.setId(gridManager.getId());
        user.setStatus(1);
        user.setRole("GridManager");

        boolean gridManagerSuccess = gridManagerService.save(gridManager);
        boolean userSuccess = userService.save(user);

        return HttpResponseEntity.response(gridManagerSuccess&&userSuccess, "创建", null);
    }

    @PostMapping("/modifyGridManager")
    public HttpResponseEntity modifyGridManager(@RequestBody RequestCharacterEntity requestCharacterEntity) {
        GridManager gridManager = requestCharacterEntity.getGridManager_create();
        User user = requestCharacterEntity.getUser_create();

        boolean gridManagerSuccess = gridManagerService.updateById(gridManager);
        boolean userSuccess = userService.updateById(user);

        return HttpResponseEntity.response(gridManagerSuccess&&userSuccess, "修改", null);
    }

    @PostMapping("/deleteGridManager")
    public HttpResponseEntity deleteGridManagerById(@RequestBody RequestCharacterEntity requestCharacterEntity) {
        GridManager gridManager = requestCharacterEntity.getGridManager_create();
        User user = requestCharacterEntity.getUser_create();

        boolean gridManagerSuccess = gridManagerService.removeById(gridManager);
        boolean userSuccess = userService.removeById(user);

        return HttpResponseEntity.response(gridManagerSuccess&&userSuccess, "删除", null);
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
