package com.repairsystem.dao;

import com.repairsystem.entity.Administrator;
import com.repairsystem.utils.MyMapper;
import org.springframework.stereotype.Component;

@Component
public interface AdministratorMapper extends MyMapper<Administrator> {

    Integer getAdministratorCount();
}