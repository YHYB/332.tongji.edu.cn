package team.scholarship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.scholarship.bean.User;
import team.scholarship.result.Result;
import team.scholarship.result.StatusEnum;
import team.scholarship.service.UserService;
import team.scholarship.util.JwtUtil;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UserController
 * @Description controller for users
 * @Author Brian.Z
 * @Date 2020/12/20 1:32
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(String userID, String password) {

        Map<String, Object> resultMap = new HashMap<>();

        User user = userService.login(userID, password);
        if (user == null) {
            return Result.ERROR(StatusEnum.WRONG_ID_OR_PWD);
        }

        JwtUtil jwtUtil = new JwtUtil();
        String token = jwtUtil.createJWT(user.getId(), user.getName(), "admin");
        resultMap.put("token", token);
        resultMap.put("userID", user.getId());
        resultMap.put("userName", user.getName());
        resultMap.put("role", user.getId().equals("admin") ? "admin" : "student");
        return Result.SUCCESS(resultMap);
    }

    @PostMapping("/login/info")
    public Result getInfoByToken(String token) {
        User user = userService.getInfo(token);
        if (user != null) {
            return Result.SUCCESS(user);
        }
        return Result.ERROR(StatusEnum.NO_DATA,"账户登录过期，请重新登陆");
    }

    @PostMapping("/logout")
    public Result logout(HttpSession httpSession) {
        httpSession.removeAttribute("token");
        return Result.SUCCESS("登出成功");
    }

    @PostMapping("/register")
    public Result register(String userID, String name, String password) {
        boolean success = userService.register(userID, name, password);

        if (success) {
            return Result.SUCCESS(userService.getUserInfo(userID));
        } else {
            return Result.ERROR(StatusEnum.DUPLICATE_PK);
        }

    }



    @PostMapping("/info")
    public User getUserInfo(String userID) {
        return userService.getUserInfo(userID);
    }
}
