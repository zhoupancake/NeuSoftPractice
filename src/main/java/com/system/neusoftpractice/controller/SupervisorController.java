package com.system.neusoftpractice.controller;

import com.system.neusoftpractice.common.HttpResponseEntity;
import com.system.neusoftpractice.dto.RequestCharacterEntity;
import com.system.neusoftpractice.dto.User;
import com.system.neusoftpractice.entity.*;
import com.system.neusoftpractice.service.*;
import com.system.neusoftpractice.util.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name="Supervisor_API")
@RestController
@RequestMapping("/supervisor")
@Slf4j
@RequiredArgsConstructor
public class SupervisorController {
    private final SupervisorService supervisorService;
    private final UserService userService;
    private final ReportService reportService;

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

    @PostMapping("report")
    public HttpResponseEntity submit(@RequestBody Report report) {
        report.setId(SnowflakeUtil.genId());
        report.setStatus(0);
        boolean success = reportService.save(report);

        return HttpResponseEntity.response(success, "提交", null);
    }

    @RequestMapping("/uploadImage")
    public Map<String, Object> imageUpload(@RequestParam("files") MultipartFile[] files) {
        Map<String, Object> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        for (MultipartFile mfile : files) {
            //获取文件后缀
            String suffixName = ImageUtil.getImagePath(mfile);
            //生成新文件名称
            String newFileName = ImageUtil.getNewFileName(suffixName);
            //保存文件
            File file = new File(ImageUtil.getNewImagePath(newFileName));
            boolean state = ImageUtil.saveImage(mfile, file);
            if (state) {
//                 list.add(ImageUtil.getNewImagePath(newFileName));
                //保存数据库的图片路径为  相对路径
                list.add("uploadImage/" + newFileName);
            }
        }
        map.put("imgList", list);
        return map;
    }

    @PostMapping("/logout")
    public HttpResponseEntity logout(HttpServletResponse response) {
        return HttpResponseEntity.response(true, "登出", null);
    }
}
