package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.entity.Storage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author llw
 * @since 2023-12-20
 */
public interface StorageService extends IService<Storage> {

    IPage<Storage> selectStoragePage(Page<Storage> page, Wrapper wrapper);
}
