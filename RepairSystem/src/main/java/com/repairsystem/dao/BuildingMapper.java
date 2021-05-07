package com.repairsystem.dao;

import com.repairsystem.entity.Building;
import com.repairsystem.utils.MyMapper;
import org.springframework.stereotype.Component;

@Component
public interface BuildingMapper extends MyMapper<Building> {

    Integer getBuildingCount();
}