package com.dhu.yumail.server.controller;

import com.dhu.yumail.server.model.base.Result;
import com.dhu.yumail.server.model.entity.Mail;
import com.dhu.yumail.server.model.vo.JobVO;
import com.dhu.yumail.server.service.api.IJobService;
import com.dhu.yumail.server.service.api.IQuartzService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 调度任务控制器
 *
 * @author Yupi Li
 * @date 19/03/22
 */
@RequestMapping("/jobs")
@RestController
public class JobController {

    @Resource
    private IJobService jobService;

    @Resource
    private IQuartzService quartzService;

    @GetMapping
    public Result<List<JobVO>> listAllJobs() {
        return Result.success(jobService.listAllJobs());
    }

    @GetMapping("/{appId}")
    public Result<List<JobVO>> listJobsByAppId(@PathVariable String appId) {
        return Result.success(jobService.listJobsByAppId(appId));
    }

    @PostMapping
    public Result addJob(@RequestBody Mail mail) {
        jobService.addJob(mail);
        return Result.success();
    }

    @PutMapping("/resume/{jobName}/{jobGroupName}")
    public Result resumeJob(@PathVariable String jobName, @PathVariable String jobGroupName) {
        quartzService.resumeJob(jobName, jobGroupName);
        return Result.success();
    }

    @PutMapping("/pause/{jobName}/{jobGroupName}")
    public Result pauseJob(@PathVariable String jobName, @PathVariable String jobGroupName) {
        quartzService.pauseJob(jobName, jobGroupName);
        return Result.success();
    }

    @DeleteMapping("/{jobName}/{jobGroupName}")
    public Result delJob(@PathVariable String jobName, @PathVariable String jobGroupName) {
        quartzService.removeJob(jobName, jobGroupName, jobName, jobGroupName);
        return Result.success();
    }


    @PutMapping("/scheduler/pause")
    public Result pauseScheduler() {
        quartzService.pauseScheduler();
        return Result.success();
    }

    @PutMapping("/scheduler/start")
    public Result startScheduler() {
        quartzService.startScheduler();
        return Result.success();
    }

    @GetMapping("/scheduler/status")
    public Result<Integer> getSchedulerStatus() {
        return Result.success(quartzService.getSchedulerStatus());
    }


}
