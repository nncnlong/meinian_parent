package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.pojo.Setmeal;
import com.atguigu.service.SetmealService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/setmeal")
public class SetmealMobileController {
    @Reference
    SetmealService setmealService;

    @RequestMapping("/getSetmealById")
    public Result getSetmealById(Integer id){
        Setmeal setmeal=setmealService.getSetmealById(id);
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,setmeal);
    }

    //显示详情时用，包含travelGroup,travelItem关联表查询
    @RequestMapping("/findById")
    public Result findById(Integer id){
        Setmeal setmeal=setmealService.findById(id);
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,setmeal);
    }

    @RequestMapping("/getSetmeal")
    public Result getSetmeal(){
        List<Setmeal> list = setmealService.getSetmeal();
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,list);
    }
}
