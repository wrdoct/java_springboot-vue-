package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.QueryPageParam;
import com.wms.common.Result;
import com.wms.entity.Goods;
import com.wms.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author llw
 * @since 2023-12-21
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @PostMapping("/page")
    public Result page(@RequestBody QueryPageParam query){
        Page<Goods> page = new Page(); //(1,2);
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        String name = (String)query.getParam().get("name");
        String storage = (String)query.getParam().get("storage");
        String goodstype = (String)query.getParam().get("goodstype");

        LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(StringUtils.isNotBlank(name) && !name.equals("null"), Goods::getName, name)
                .eq(StringUtils.isNotBlank(storage) && !storage.equals("null"), Goods::getStorage, storage)
                .eq(StringUtils.isNotBlank(goodstype) && !goodstype.equals("null"), Goods::getGoodstype, goodstype);

        IPage<Goods> result = goodsService.selectGoodsPage(page, lambdaQueryWrapper); // 使用条件构造器作为参数

        return Result.success(result.getTotal(), result.getRecords());
    }

    //新增
    @PostMapping("/add")
    public Result add(@RequestBody Goods goods){
        return goodsService.save(goods) ? Result.success() : Result.fail();
    }
    //更新
    @PutMapping("/update")
    public Result update(@RequestBody Goods goods){
        return goodsService.updateById(goods) ? Result.success() : Result.fail();
    }
    //删除
    @DeleteMapping("/del")
    public Result del(@RequestParam String id){
        return goodsService.removeById(id) ? Result.success() : Result.fail();
    }
}
