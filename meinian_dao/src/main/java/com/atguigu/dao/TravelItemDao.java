package com.atguigu.dao;

import com.atguigu.pojo.TravelItem;
import com.github.pagehelper.Page;

import java.util.List;

public interface TravelItemDao {
    void add(TravelItem travelItem);

    Page findPage(String queryString);

    void delete(Integer id);

    TravelItem getById(Integer id);

    void edit(TravelItem travelItem);

    List<TravelItem> findAll();

    long findCountByTravelItemId(Integer id);

    /**
     * 帮助封装跟团游的travelItems属性
     * @param id    跟团游id
     * @return  这个跟团游对应的多个自由行数据
     */
    List<TravelItem> findTravelItemById(Integer id);
}
