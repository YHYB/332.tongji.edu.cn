package com.learn.demo.springbootdemo.controller;

import com.learn.demo.springbootdemo.common.PageResult;
import com.learn.demo.springbootdemo.common.Result;
import com.learn.demo.springbootdemo.exception.BusinessException;
import com.learn.demo.springbootdemo.pojo.User;
import com.learn.demo.springbootdemo.service.UserService;
import com.learn.demo.springbootdemo.util.EasyPoiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author chenbin
 * @description: 控制器层
 * @version: V1.0
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 查询全部数据
     *
     * @return
     */
    @GetMapping()
    public Result findAll() {
        return Result.SUCCESS("查询成功", userService.findAll());
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @GetMapping(value = "/{id}")
    public Result findById(@PathVariable String id) {
        return Result.SUCCESS("查询成功", userService.findById(id));
    }


    /**
     * 分页+多条件查询
     *
     * @param searchMap 查询条件封装
     * @param page      页码
     * @param size      页大小
     * @return 分页结果
     */
    @PostMapping(value = "/search/{page}/{size}")
    public Result findSearch(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size) {
        Page<User> pageList = userService.findSearch(searchMap, page, size);
        return Result.SUCCESS("查询成功", new PageResult<User>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @PostMapping(value = "/search")
    public Result findSearch(@RequestBody Map searchMap) {
        return Result.SUCCESS("查询成功", userService.findSearch(searchMap));
    }

    /**
     * 增加
     *
     * @param user
     */
    @PostMapping()
    public Result add(@RequestBody User user) {
        if (userService.add(user)) {
            return Result.SUCCESS("增加成功");
        }

        return Result.ERROR("用户名已存在");
    }

    /**
     * 修改
     *
     * @param user
     */
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody User user, @PathVariable String id) {
        user.setId(id);
        userService.update(user);
        return Result.SUCCESS("修改成功");
    }

    /**
     * 删除
     *
     * @param id
     */
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable String id) {
        userService.deleteById(id);
        return Result.SUCCESS("删除成功");
    }

    /**
     * 导出
     *
     * @param response
     */
    @GetMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response, String name) {
        EasyPoiUtil.exportExcel(response, userService.findAll(), User.class, name);
    }

    /**
     * 导入
     *
     * @param file
     */
    @PostMapping("/importExcel")
    @Transactional(rollbackFor = BusinessException.class)
    public Result importExcel(@RequestParam("file") MultipartFile file) throws BusinessException {
        List list = EasyPoiUtil.importExcel(file, User.class);
        if (list == null) {
            return Result.ERROR("导入失败");
        }
        for (Object object : list) {
            if (!userService.add((User) object)) {
                throw new BusinessException("导入失败,部分用户名已存在");
            }
        }
        return Result.SUCCESS("导入成功");
    }
}
