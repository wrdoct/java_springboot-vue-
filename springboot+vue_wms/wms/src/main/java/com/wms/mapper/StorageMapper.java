package com.wms.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.entity.Storage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wms.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author llw
 * @since 2023-12-20
 */
@Mapper
public interface StorageMapper extends BaseMapper<Storage> {

    //    @Select("select * from storage ${ew.customSqlSegment}") // 使用条件构造器作为参数
    IPage<Storage> selectStoragePage(Page<Storage> page, @Param(Constants.WRAPPER) Wrapper wrapper); // 使用条件构造器作为参数
}
