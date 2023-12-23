package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.QueryPageParam;
import com.wms.common.Result;
import com.wms.entity.Goodstype;
import com.wms.service.GoodstypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author llw
 * @since 2023-12-21
 */
@RestController
@RequestMapping("/goodstype")
public class GoodstypeController {

    @Autowired
    private GoodstypeService goodstypeService;

    @PostMapping("/page")
    public Result page(@RequestBody QueryPageParam query){
        Page<Goodstype> page = new Page(); //(1,2);
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        String name = (String)query.getParam().get("name");

        LambdaQueryWrapper<Goodstype> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(StringUtils.isNotBlank(name) && !name.equals("null"), Goodstype::getName, name);

        IPage<Goodstype> result = goodstypeService.selectGoodstypePage(page, lambdaQueryWrapper); // 使用条件构造器作为参数

        return Result.success(result.getTotal(), result.getRecords());
    }

    @GetMapping("/list")
    public Result list(){
        List<Goodstype> list = goodstypeService.lambdaQuery().list();
        return Result.success(list);
    }

    //新增
    @PostMapping("/add")
    public Result add(@RequestBody Goodstype goodstype){
        return goodstypeService.save(goodstype) ? Result.success() : Result.fail();
    }
    //更新
    @PutMapping("/update")
    public Result update(@RequestBody Goodstype goodstype){
        return goodstypeService.updateById(goodstype) ? Result.success() : Result.fail();
    }
    //删除
    @DeleteMapping("/del")
    public Result del(@RequestParam String id){
        return goodstypeService.removeById(id) ? Result.success() : Result.fail();
    }

}
