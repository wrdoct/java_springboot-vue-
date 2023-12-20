package com.wms.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.QueryPageParam;
import com.wms.common.Result;
import com.wms.entity.Menu;
import com.wms.entity.User;
import com.wms.service.MenuService;
import com.wms.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    public Result list(){
        return Result.success(userService.list());
    }

    @PostMapping("/pageC")
//    public List<User> pageC(@RequestBody QueryPageParam query){
    public Result pageC(@RequestBody QueryPageParam query){
        Page<User> page = new Page(); //(1,2);
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        String name = (String)query.getParam().get("name");
        String sex = (String)query.getParam().get("sex");
        String roleId = (String)query.getParam().get("roleId");

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        if (StringUtils.isNotBlank(name) && !name.equals("null")){
            lambdaQueryWrapper.like(User::getName, name);
        }
        if (StringUtils.isNotBlank(sex) && !sex.equals("null")){
            lambdaQueryWrapper.eq(User::getSex, sex);
        }
        if (StringUtils.isNotBlank(roleId) && !roleId.equals("null")){
            lambdaQueryWrapper.eq(User::getRoleId, roleId);
        }

//        IPage<User> result = userService.pageC(page);
        IPage<User> result = userService.pageCC(page, lambdaQueryWrapper);
        System.out.println("total = " + result.getTotal());

//        return result.getRecords();
        return Result.success(result.getTotal(), result.getRecords());
    }

    @GetMapping("/findByNo")
    public Result findByNo(@RequestParam(value = "no") String no){
        List<User> list = userService.lambdaQuery().eq(User::getNo, no).list();
        return list.size() > 0 ? Result.success(list) : Result.fail();
    }

    //新增
    @PostMapping("/add")
    public Result add(@RequestBody User user){
        return userService.save(user) ? Result.success() : Result.fail();
    }
    //更新
    @PutMapping("/update")
    public Result update(@RequestBody User user){
        return userService.updateById(user) ? Result.success() : Result.fail();
    }
    //删除
    @DeleteMapping("/del")
    public Result del(@RequestParam String id){
        return userService.removeById(id) ? Result.success() : Result.fail();
    }

    //登录
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        List<User> userList = userService.lambdaQuery()
                .eq(User::getNo, user.getNo())
                .eq(User::getPassword, user.getPassword()).list();

        if (userList.size()>0){
            User user1 = userList.get(0);
            List<Menu> menuList = menuService.lambdaQuery().like(Menu::getMenuright, user1.getRoleId()).list();
            HashMap res = new HashMap();
            res.put("user", user1);
            res.put("menu", menuList);
            return Result.success(res);
        }

        return Result.fail();
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
        if (StringUtils.isNotBlank(name) && !name.equals("null")){
            lambdaQueryWrapper.like(User::getName, name);
        }
        IPage<User> result = userService.page(page, lambdaQueryWrapper);
        System.out.println("total = " + result.getTotal());

        return result.getRecords();
    }
}
