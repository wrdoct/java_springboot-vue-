package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.QueryPageParam;
import com.wms.common.Result;
import com.wms.entity.Goods;
import com.wms.entity.Record;
import com.wms.entity.Record;
import com.wms.service.GoodsService;
import com.wms.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author llw
 * @since 2023-12-23
 */
@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @Autowired
    private GoodsService goodsService;

    @PostMapping("/page")
    public Result page(@RequestBody QueryPageParam query){
        Page<Record> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        String name = (String)query.getParam().get("name");
        String storage = (String)query.getParam().get("storage");
        String goodstype = (String)query.getParam().get("goodstype");

        QueryWrapper<Record> queryWrapper = new QueryWrapper();
        queryWrapper.apply(" r.goods=g.id\n" +
                "         and s.id= g.storage\n" +
                "         and gt.id=g.goodsType ");
        queryWrapper.like(StringUtils.isNotBlank(name) && !"null".equals(name),"g.name", name)
                .eq(StringUtils.isNotBlank(storage) && !"null".equals(storage),"s.id", storage)
                .eq(StringUtils.isNotBlank(goodstype) && !"null".equals(goodstype),"gt.id", goodstype);

        IPage<Record> result = recordService.selectRecordPage(page, queryWrapper); // 使用条件构造器作为参数

        return Result.success(result.getTotal(), result.getRecords());
    }

    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Record record){
        Date date = new Date();
        record.setCreatetime(date);

        Integer num = record.getCount();
        Goods goods = goodsService.getById(record.getGoods());
        if(record.getAction().equals("1")){ // 入库
            goods.setCount(goods.getCount() + num);
        }else if(record.getAction().equals("2")){ //出库
            goods.setCount(goods.getCount() - num);
            record.setCount(-num);
        }
        goodsService.updateById(goods);
        return recordService.save(record) ? Result.success() : Result.fail();
    }
}
