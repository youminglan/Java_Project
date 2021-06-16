package com.atguigu.eduservice.controller;


import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.management.Agent;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-06-16
 */

@Api(description="讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    //访问地址：http://localhost:8001/eduservice/teacher/findAll

    //把service注入
    @Autowired
    private EduTeacherService teacherService;

    @ApiOperation(value = "所有讲师列表")
    //查询讲师表中的所有数据
    //rest风格
    @GetMapping("findAll")
    public List<EduTeacher> findAllTeacher(){
        //调用service方法实现查询所有的操作
        List<EduTeacher> list = teacherService.list(null);
        return list;
    }

    //逻辑删除讲师的方法
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}") //id值需要通过路径进行传递
    public boolean removeTeacher(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){
        boolean flag = teacherService.removeById(id);
        return flag;
    }
}

