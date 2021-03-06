package com.repairsystem.service;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.repairsystem.entity.Class;
import com.repairsystem.entity.vo.ClassVO;

import java.util.List;

/**
 * @author youminglan
 * @date 2021/04/21
 * @time 22:41
 */
public interface ClassService {

    /**
     * 获取全部机房信息
     *
     * @return
     */
    List<Class> searchAllClass();

    /**
     * 通过机房ID获取机房信息
     *
     * @param id
     * @return
     */
    Class searchClassById(Integer id);

    /**
     * 通过机房名称获取机房信息
     *
     * @param name
     * @return
     */
    List<Class> searchClassByName(String name);

    /**
     * 通过实训楼ID称获取机房信息
     *
     * @param buildingId
     * @return
     */
    List<Class> searchClassByBuildingId(String buildingId);

    Integer getClassCount();

    /**
     * 保存机房信息
     *
     * @param classes
     */
    void saveClass(Class classes);

    /**
     * 修改你机房信息
     *
     * @param classes
     */
    void updateClass(Class classes);

    /**
     * 删除机房信息
     *
     * @param id
     */
    void deleteClass(Integer id) throws MySQLIntegrityConstraintViolationException;

    /**
     * 增加实训室可用电脑
     *
     * @param id
     */
    void increaseComputerEnable(Integer id);

    /**
     * 减少实训室可用电脑
     *
     * @param id
     */
    void reduceComputerEnable(Integer id);

}
