package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.entity.Goodstype;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author llw
 * @since 2023-12-21
 */
public interface GoodstypeService extends IService<Goodstype> {
    IPage<Goodstype> selectGoodstypePage(Page<Goodstype> page, Wrapper wrapper);
}
