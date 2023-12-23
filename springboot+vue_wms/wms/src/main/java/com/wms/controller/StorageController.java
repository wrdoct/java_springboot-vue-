package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.QueryPageParam;
import com.wms.common.Result;
import com.wms.entity.Storage;
import com.wms.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author llw
 * @since 2023-12-20
 */
@RestController
@RequestMapping("/storage")
public class StorageController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/page")
    public Result page(@RequestBody QueryPageParam query){
        Page<Storage> page = new Page(); //(1,2);
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        String name = (String)query.getParam().get("name");

        LambdaQueryWrapper<Storage> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(StringUtils.isNotBlank(name) && !name.equals("null"), Storage::getName, name);

        IPage<Storage> result = storageService.selectStoragePage(page, lambdaQueryWrapper); // 使用条件构造器作为参数

        return Result.success(result.getTotal(), result.getRecords());
    }

    @GetMapping("/list")
    public Result list(){
        List<Storage> list = storageService.lambdaQuery().list();
        return Result.success(list);
    }

    //新增
    @PostMapping("/add")
    public Result add(@RequestBody Storage storage){
        return storageService.save(storage) ? Result.success() : Result.fail();
    }
    //更新
    @PutMapping("/update")
    public Result update(@RequestBody Storage storage){
        return storageService.updateById(storage) ? Result.success() : Result.fail();
    }
    //删除
    @DeleteMapping("/del")
    public Result del(@RequestParam String id){
        return storageService.removeById(id) ? Result.success() : Result.fail();
    }

}
