package com.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.entity.Goodstype;
import com.wms.mapper.GoodstypeMapper;
import com.wms.service.GoodstypeService;
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
public class GoodstypeServiceImpl extends ServiceImpl<GoodstypeMapper, Goodstype> implements GoodstypeService {
    @Resource
    private GoodstypeMapper goodstypeMapper;

    @Override
    public IPage<Goodstype> selectGoodstypePage(Page<Goodstype> page, Wrapper wrapper) {
        return goodstypeMapper.selectGoodstypePage(page, wrapper);
    }
}
