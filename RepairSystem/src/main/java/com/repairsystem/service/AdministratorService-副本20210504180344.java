package com.repairsystem.service;

import com.repairsystem.entity.Administrator;

import java.util.List;

/**
 * @author youminglan
 * @date 2021/04/20
 * @time 19:41
 */
public interface AdministratorService {

    /**
     * 查询所有管理员账户
     * @return List<Administrator>
     */
    List<Administrator> searchAllAdministrator();

    String countAllAdministrator();



    /**
     * 按管理员ID搜索管理员
     * @param id (管理员ID，Integer类型)
     * @return Administrator
     */
    Administrator searchAdministratorById(Integer id);

    /**
     * 按照管理员姓名搜索管理员
     * @param name (管理员姓名，String类型)
     * @return List<Administrator>
     */
    List<Administrator> searchAdministratorByName(String name);

    /**
     * 按照手机号搜索管理员
     * @param phoneNum
     * @return String
     */
    Administrator searchAdministratorByPhoneNum(String phoneNum);

    /**
     * 管理员登录
     * @param phone
     * @param password
     * @return String
     */
    Administrator loginAdministrator(String phone, String password);

    /**
     * 查询管理员手机是否存在
     * @param number
     * @return boolean
     */
    boolean administratorPhoneNumberIsExist(String number);

    /**
     * 添加管理员
     * @param admin
     */
    void saveAdministrator(Administrator admin);

    /**
     * 修改管理员
     * @param admin
     */
    void updateAdministrator(Administrator admin);

    /**
     * 删除管理员
     * @param id
     */
    void deleteAdministrator(Integer id);


}
