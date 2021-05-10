package com.repairsystem.utils;

import org.springframework.beans.BeanUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * @author youminglan
 * @date 2021/04/22
 * @time 11:44
 */

//什么是VO类。
//VO类通常用于业务层之间的数据传递，就是负责给前端看的，
//在上面我们曾经提到过有一些字段是不能够展示的，
//如果改动实体类，那样实体类和数据库的字段不一致，导致不能正常对数据库进行查询，
//所以我们需要创建一个VO类

public class Entity2VO {

    /**
     * 实体类列表转换为 VO类列表
     * @param entityList
     * @param voClass
     * @return
     */
    public static List entityList2VOList(List<? extends Object> entityList, Class voClass){
        List voList = new LinkedList();
        Object voObj = null;
        for(Object entityObj:entityList){
            try {
                voObj = voClass.newInstance();

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            BeanUtils.copyProperties(entityObj,voObj);
            voList.add(voObj);
        }
        return voList;
    }

    /**
     * 实体类转换为 VO类
     * @param entity
     * @param voClass
     * @param <T>
     * @return
     */
    public static <T> T entity2VO(Object entity,Class<T> voClass){
        T vo = null;
        try {
            vo = voClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        BeanUtils.copyProperties(entity,vo);
        return vo;
    }
}
