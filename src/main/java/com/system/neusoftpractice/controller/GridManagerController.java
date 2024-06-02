package com.system.neusoftpractice.controller;

import com.system.neusoftpractice.common.HttpResponseEntity;
import com.system.neusoftpractice.dto.RequestCharacterEntity;
import com.system.neusoftpractice.dto.User;
import com.system.neusoftpractice.entity.GridManager;
import com.system.neusoftpractice.entity.Task;
import com.system.neusoftpractice.service.GridManagerService;
import com.system.neusoftpractice.service.TaskService;
import com.system.neusoftpractice.service.UserService;
import com.system.neusoftpractice.util.ImageUtil;
import com.system.neusoftpractice.util.SnowflakeUtil;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;

@Tag(name="GridManager_API")
@RestController
@RequestMapping("/gridManager")
@Slf4j
@RequiredArgsConstructor
public class GridManagerController {
    private final GridManagerService gridManagerService;
    private final UserService userService;
    private final TaskService taskService;

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

    @PostMapping("submit")
    public HttpResponseEntity submit(@RequestBody Task task) {
        boolean success = taskService.updateById(task);

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
