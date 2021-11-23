package com.yupi.yumail.client.model.base;

import com.yupi.yumail.client.constant.YumailConstant;
import com.yupi.yumail.client.properties.YumailClientProperties;
import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * 功能描述：Yumail Server信息
 *
 * @author Yupi Li
 * @date 2019/4/19 23:58
 */
@Repository
@Data
public class ServerInfo {


    @Resource
    private YumailClientProperties yumailClientProperties;

    /**
     * 连接状态（默认0-未连接 1-正常连接）
     */
    Integer status;

    public String getUrl() {
        return yumailClientProperties.getServer().getUrl() + YumailConstant.SERVER_REQUEST_PATH;
    }
}
