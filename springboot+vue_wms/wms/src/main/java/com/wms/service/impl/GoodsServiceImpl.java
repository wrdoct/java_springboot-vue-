package com.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.entity.Goods;
import com.wms.entity.Goods;
import com.wms.mapper.GoodsMapper;
import com.wms.mapper.GoodsMapper;
import com.wms.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author llw
 * @since 2023-12-21
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {
    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public IPage<Goods> selectGoodsPage(Page<Goods> page, Wrapper wrapper) {
        return goodsMapper.selectGoodsPage(page, wrapper);
    }
}
