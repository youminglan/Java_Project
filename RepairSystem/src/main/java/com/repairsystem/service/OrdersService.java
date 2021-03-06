package com.repairsystem.service;

import com.repairsystem.entity.Orders;
import com.repairsystem.entity.vo.OrderVO;

import java.util.List;

/**
 * @author CheungChingYin
 * @date 2021/04/21
 * @time 18:43
 */
public interface OrdersService {

    /**
     * 获取全部维修单
     *
     * @return
     */
    List<Orders> searchAllOrder();

    /**
     * 按照维修单号搜索维修单
     *
     * @param id
     * @return
     */
    Orders searchOrderById(Integer id);

    /**
     * 获得工单总数量
     *
     * @return
     */
    Integer getOrderCount();

    /**
     * 添加维修单
     *
     * @param order
     */
    void saveOrder(Orders order);

    /**
     * 更新维修单
     *
     * @param order
     */
    void updateOrder(Orders order);

    /**
     * 删除维修单
     *
     * @param id
     */
    void deleteOrder(Integer id);

}
