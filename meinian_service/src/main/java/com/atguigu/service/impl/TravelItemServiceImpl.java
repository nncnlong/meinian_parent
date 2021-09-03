package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.TravelItemDao;
import com.atguigu.entity.PageResult;
import com.atguigu.pojo.TravelItem;
import com.atguigu.service.TravelItemService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = TravelItemService.class) //发布服务，注册到zookeeper
@Transactional //声明式事务，所有方法都加上事务
public class TravelItemServiceImpl implements TravelItemService {
    @Autowired  //这个对象被mapper映射文件创建了，因此没有开启包扫描也能注入
    TravelItemDao travelItemDao;

    @Override
    public void add(TravelItem travelItem) {
        travelItemDao.add(travelItem);
    }

    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        //分页插件
        //开启分页功能
        //limit (currentPage-1)*pageSize,pageSize
        PageHelper.startPage(currentPage,pageSize);// limit ?,? 第一个?表示开始的索引，第二个表示查询的条数
        Page page = travelItemDao.findPage(queryString);//返回当前页数据
        return new PageResult(page.getTotal(),page.getResult());//返回总记录数和查询到的分页数据集合
    }

    @Override
    public void delete(Integer id) {//自由行id
        //查自由行关联表中是否存在关联数据，如果存在就抛异常，不进行删除
        long count = travelItemDao.findCountByTravelItemId(id);
        if(count>0){//有关联数据
            throw new RuntimeException("删除自由行失败，因为存在关联数据。请先解除关系再删除！");
        }else{
            travelItemDao.delete(id);
        }
    }

    @Override
    public TravelItem getById(Integer id) {
         return travelItemDao.getById(id);

    }

    @Override
    public void edit(TravelItem travelItem) {
        travelItemDao.edit(travelItem);
    }

    @Override
    public List<TravelItem> findAll() {
        return travelItemDao.findAll();
    }
}
