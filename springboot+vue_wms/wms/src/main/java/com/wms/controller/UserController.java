package com.wms.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.QueryPageParam;
import com.wms.common.Result;
import com.wms.entity.User;
import com.wms.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author llw
 * @since 2023-11-23
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public List<User> list(){
        return userService.list();
    }

    //新增
    @PostMapping("/add")
    public boolean add(@RequestBody User user){
        return userService.save(user);
    }
    //修改
    @PutMapping("/mod")
    public boolean mod(@RequestBody User user){
        return userService.updateById(user);
    }
    //新增或修改
    @PutMapping("/addOrMod")
    public boolean addOrMod(@RequestBody User user){
        return userService.saveOrUpdate(user);
    }
    //删除
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") Integer id){
        return userService.removeById(id);
    }
    //查询（模糊、匹配）
    @PostMapping("/list1")
    public List<User> list(@RequestBody User user){
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(user.getName())){
            lambdaQueryWrapper.like(User::getName, user.getName());
        }
        return userService.list(lambdaQueryWrapper);
    }
    @GetMapping("/page")
    public List<User> page(@RequestBody QueryPageParam query){
        Page<User> page = new Page(); //(1,2);
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        String name = (String)query.getParam().get("name");
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(User::getName, name);
        IPage<User> result = userService.page(page, lambdaQueryWrapper);
        System.out.println("total = " + result.getTotal());

        return result.getRecords();
    }

    @GetMapping("/pageC")
//    public List<User> pageC(@RequestBody QueryPageParam query){
    public Result pageC(@RequestBody QueryPageParam query){
        Page<User> page = new Page(); //(1,2);
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        String name = (String)query.getParam().get("name");
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(User::getName, name);
//        IPage<User> result = userService.pageC(page);
        IPage<User> result = userService.pageCC(page, lambdaQueryWrapper);
        System.out.println("total = " + result.getTotal());

//        return result.getRecords();
        return Result.success(result.getTotal(), result.getRecords());
    }
}
