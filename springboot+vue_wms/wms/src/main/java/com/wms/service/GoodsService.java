package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.entity.Goods;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author llw
 * @since 2023-12-21
 */
public interface GoodsService extends IService<Goods> {
    IPage<Goods> selectGoodsPage(Page<Goods> page, Wrapper wrapper);
}
