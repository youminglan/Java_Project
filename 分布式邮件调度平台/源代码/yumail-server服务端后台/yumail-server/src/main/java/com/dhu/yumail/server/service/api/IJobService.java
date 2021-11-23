package com.dhu.yumail.server.service.api;

import com.dhu.yumail.server.model.entity.Mail;
import com.dhu.yumail.server.model.vo.JobVO;

import java.util.List;

/**
 * 任务服务
 * @author Yupi Li
 * @date 19/03/23
 */
public interface IJobService {

    /**
     * 获取所有Job
     * @return
     */
    List<JobVO> listAllJobs();

    /**
     * 根据调度源appId获取Job
     * @param appId
     * @return
     */
    List<JobVO> listJobsByAppId(String appId);

    void addJob(Mail mail);
}
