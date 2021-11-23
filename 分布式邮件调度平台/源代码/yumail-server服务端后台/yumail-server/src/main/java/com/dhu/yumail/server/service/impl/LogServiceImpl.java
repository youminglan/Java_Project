package com.dhu.yumail.server.service.impl;

import com.dhu.yumail.server.constant.LogConstant;
import com.dhu.yumail.server.core.YumailContext;
import com.dhu.yumail.server.model.entity.Log;
import com.dhu.yumail.server.service.api.ILogService;
import com.dhu.yumail.server.utils.DateFormatter;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 邮件服务实现类
 *
 * @author Yupi Li
 * @date 19/03/16
 */
@Service
public class LogServiceImpl implements ILogService {

    @Override
    public void error(String appId, String content) {
        log(appId, content, LogConstant.ERROR_LEVEL);
    }

    @Override
    public void warning(String appId, String content) {
        log(appId, content, LogConstant.WARNING_LEVEL);
    }

    @Override
    public void info(String appId, String content) {
        log(appId, content, LogConstant.INFO_LEVEL);
    }

    @Override
    public void log(String appId, String content, Integer level) {
        Map<String, List<Log>> nameLogListMap = YumailContext.getNameLogListMap();
        Log log = new Log(appId, content, level);
        List<Log> logs = nameLogListMap.getOrDefault(appId, new ArrayList<>());
        logs.add(log);
        nameLogListMap.putIfAbsent(appId, logs);
    }

    @Override
    public String doPersist(HttpServletResponse response, String appId) {
        // 生成日志文件
        File file = genLogFileByAppId(appId);
        // todo 暂不支持下载
        return file.getAbsolutePath();
    }

    private File genLogFileByAppId(String appId) {
        String fileName = System.getProperty("user.dir") + "\\tmp\\logs\\"
                + appId + "-"
                + DateFormatUtils.format(new Date(), "yyyy-MM-dd")
                + ".log";
        File file = new File(fileName);
        try {
            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWritter = new FileWriter(file);
            List<Log> logList = YumailContext.getNameLogListMap().getOrDefault(appId, new ArrayList<>());
            for (Log log : logList) {
                fileWritter.write(String.format("时间：%s 级别：%s 调度源：%s\n日志内容：%s\n",
                        DateFormatter.getISODateStr(log.getCreateTime()),
                        log.getLevel(), log.getAppId(), log.getContent()));
            }
            fileWritter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

}
