package com.wms.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.entity.Goodstype;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author llw
 * @since 2023-12-21
 */
@Mapper
public interface GoodstypeMapper extends BaseMapper<Goodstype> {

    IPage<Goodstype> selectGoodstypePage(Page<Goodstype> page, @Param(Constants.WRAPPER) Wrapper wrapper);
}
