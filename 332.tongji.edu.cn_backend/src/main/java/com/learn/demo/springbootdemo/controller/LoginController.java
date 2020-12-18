package com.learn.demo.springbootdemo.controller;

import com.learn.demo.springbootdemo.common.Result;
import com.learn.demo.springbootdemo.pojo.User;
import com.learn.demo.springbootdemo.service.UserService;
import com.learn.demo.springbootdemo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenbin
 * @description: 用户登录
 * @version: V1.0
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    /**
     * 登录
     *
     * @param user 用户实体
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        user = userService.login(user.getUsername(), user.getPassword());
        if (user == null) {
            return Result.ERROR("登录失败,账号或密码不正确");
        }
        String token = jwtUtil.createJWT(user.getId(), user.getUsername(), "admin");
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("token", token);
        resultMap.put("name", user.getUsername());
        List<String> list = new ArrayList<>();
        resultMap.put("roles", list);
        resultMap.put("uid", user.getId());
        return Result.SUCCESS("登录成功", resultMap);
    }

    /**
     * 修改密码
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/update/password")
    public Result updatePassword(@RequestBody User user) {
        userService.updatePassword(user);
        return Result.SUCCESS("密码修改成功");
    }

    /**
     * 获取用户信息
     *
     * @param token 令牌
     * @return
     */
    @PostMapping(value = "/info")
    public Result getInfo(String token) {
        Map<String, Object> map = userService.getInfo(token);
        if (map != null) {
            return Result.SUCCESS("获取成功", map);
        }
        return Result.ERROR("token失效，请重新登录");
    }

    /**
     * 退出登录
     *
     * @param session
     * @return
     */
    @GetMapping(value = "/logout")
    public Result getInfo(HttpSession session) {
        session.removeAttribute("token");
        return Result.SUCCESS("退出成功");
    }

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/register")
    public Result register(@RequestBody User user) {
        if (userService.register(user)) {
            return Result.SUCCESS("注册成功");
        }
        return Result.ERROR("用户名已存在");
    }
}
