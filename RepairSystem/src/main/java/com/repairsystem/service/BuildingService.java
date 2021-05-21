package com.repairsystem.service;

import com.repairsystem.entity.Building;

import java.util.List;

/**
 * @author youminglan
 * @date 2021/04/22
 * @time 09:41
 */
public interface BuildingService {

    /**
     * 获取所有教学楼信息
     *
     * @return
     */
    List<Building> searchAllBuilding();

    /**
     * 通过教学楼编号获取教学楼信息
     *
     * @param id
     * @return
     */
    Building searchBuildingById(Integer id);

    /**
     * 通过教学楼名字获取教学楼信息
     *
     * @param name
     * @return
     */
    List<Building> searchBuildingByName(String name);

    /**
     * 获得实训楼总数
     *
     * @return
     */
    Integer getBuildingCount();

    /**
     * 保存教学楼信息
     *
     * @param building
     */
    void savBuilding(Building building);

    /**
     * 更新教学楼信息
     *
     * @param building
     */
    void updateBuilding(Building building);

    /**
     * 删除教学楼信息
     *
     * @param id
     */
    void deleteBuilding(Integer id);


}
