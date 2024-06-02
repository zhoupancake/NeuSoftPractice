package com.system.neusoftpractice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.neusoftpractice.common.HttpResponseEntity;
import com.system.neusoftpractice.entity.Task;
import com.system.neusoftpractice.service.TaskService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Tag(name="Task_API")
@RestController
@RequestMapping("/task")
@Slf4j
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    @PostMapping("/addTask")
    public HttpResponseEntity addTask(@RequestBody Task task) {
        boolean success = taskService.save(task);
        return HttpResponseEntity.response(success, "创建", null);
    }

    @PostMapping("/modifyTask")
    public HttpResponseEntity modifyTask(@RequestBody Task task) {
        boolean success = taskService.updateById(task);
        return HttpResponseEntity.response(success, "修改", null);
    }

    @PostMapping("/deleteTask")
    public HttpResponseEntity deleteTaskById(@RequestBody Task task) {
        boolean success = taskService.removeById(task);
        return HttpResponseEntity.response(success, "删除", null);
    }

    @PostMapping("/queryTaskList")
    public HttpResponseEntity queryTaskList(@RequestBody Map<String, Object> map) {
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        Page<Task> page = new Page<>(pageNum, pageSize);
        taskService.query().eq("status", "1")
                .like("username", map.get("username")).page(page);
        List<Task> taskList = page.getRecords();
        boolean success = !taskList.isEmpty();
        return HttpResponseEntity.response(success, "查询", taskList);
    }
}
