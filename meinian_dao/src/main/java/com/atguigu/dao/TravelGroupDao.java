package com.atguigu.dao;

import com.atguigu.pojo.TravelGroup;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TravelGroupDao {
    void add(TravelGroup travelGroup);

    void addTravelGroupAndTravelItem(Map<String, Integer> paramData);

    Page findPage(@Param("queryString") String queryString);

    TravelGroup getById(Integer id);

    List<Integer> getTravelItemIdsByTravelGroupId(Integer travelGroupId);

    void edit(TravelGroup travelGroup);

    void delete(Integer travelGroupId);

    void deleteTravelGroup(Integer travelGroupId);

    List<TravelGroup> findAll();

    /**
     * 帮助封装套餐对象的travelGroups
     * @param id    套餐的id
     * @return  这个套餐对应的多个跟团游数据
     */
    List<TravelGroup> findTravelGroupById(Integer id);
}
