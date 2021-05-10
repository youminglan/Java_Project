package com.repairsystem.service;

import com.repairsystem.entity.Role;

import java.util.List;

/**
 * @author youminglan
 * @date 2021/04/21
 * @time 9:59
 */
public interface RoleService {

    /**
     * 获得全部角色信息
     * @return
     */
    List<Role> searchAllRole();

    /**
     * 通过角色ID获取角色信息
     * @param id
     * @return
     */
    Role searchRoleById(Integer id);
}
