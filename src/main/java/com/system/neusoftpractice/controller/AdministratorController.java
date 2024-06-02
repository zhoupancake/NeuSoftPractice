package com.system.neusoftpractice.controller;

import com.system.neusoftpractice.dto.RequestCharacterEntity;
import com.system.neusoftpractice.dto.User;
import com.system.neusoftpractice.entity.Administrator;
import com.system.neusoftpractice.service.AdministratorService;
import com.system.neusoftpractice.common.*;
import com.system.neusoftpractice.service.UserService;
import com.system.neusoftpractice.util.*;

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
    private final UserService userService;

    @PostMapping("/addAdministrator")
    public HttpResponseEntity addAdministrator(@RequestBody RequestCharacterEntity requestCharacterEntity) {
        Administrator administrator = requestCharacterEntity.getAdministrator_create();
        User user = requestCharacterEntity.getUser_create();

        administrator.setId(SnowflakeUtil.genId());
        user.setId(administrator.getId());
        user.setStatus(1);
        user.setRole("Administrator");

        boolean administratorSuccess = administratorService.save(administrator);
        boolean userSuccess = userService.save(user);

        return HttpResponseEntity.response(administratorSuccess&&userSuccess, "创建", null);
    }

    @PostMapping("/modifyAdministrator")
    public HttpResponseEntity modifyAdministrator(@RequestBody RequestCharacterEntity requestCharacterEntity) {
        Administrator administrator = requestCharacterEntity.getAdministrator_modify();
        User user = requestCharacterEntity.getUser_modify();

        boolean administratorSuccess = administratorService.updateById(administrator);
        boolean userSuccess = userService.updateById(user);
        return HttpResponseEntity.response(administratorSuccess&&userSuccess, "修改", null);
    }

    @PostMapping("/deleteAdministrator")
    public HttpResponseEntity deleteAdministratorById(@RequestBody RequestCharacterEntity requestCharacterEntity) {
        Administrator administrator = requestCharacterEntity.getAdministrator_modify();
        User user = requestCharacterEntity.getUser_modify();

        boolean administratorSuccess = administratorService.removeById(administrator);
        boolean userSuccess = userService.removeById(user);
        return HttpResponseEntity.response(administratorSuccess&&userSuccess, "删除", null);
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
    public HttpResponseEntity logout(@RequestBody Administrator administrator, HttpServletResponse response) {
        return HttpResponseEntity.response(true, "登出", null);
    }


}
