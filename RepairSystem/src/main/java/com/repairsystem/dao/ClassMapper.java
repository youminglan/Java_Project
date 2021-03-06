package com.repairsystem.dao;

import com.repairsystem.entity.Class;
import com.repairsystem.entity.vo.ClassVO;
import com.repairsystem.utils.MyMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ClassMapper extends MyMapper<Class> {

    List<Class> getAllClass();

    Class getClassById(Integer id);

    List<Class> getClassByName(String name);

    List<Class> getClassByBuildingId(String buildingId);

    Integer getClassCount();
}