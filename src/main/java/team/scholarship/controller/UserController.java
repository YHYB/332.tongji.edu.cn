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
import java.util.List;
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

        Map<String, Object> resultMap = new HashMap<>();

        User user = userService.getInfo(token);
        if (user != null) {
            return Result.SUCCESS(user);
        }
        return Result.ERROR(StatusEnum.NO_DATA, "账户登录过期，请重新登陆");
    }

    @GetMapping("/logout")
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
    public Result getUserInfo(String userID) {
        User user = userService.getUserInfo(userID);

        if (user == null) {
            return Result.ERROR(StatusEnum.NO_DATA, null);
        } else {
            return Result.SUCCESS(user);
        }
    }

    @PostMapping("/update")
    public Result update(String userID, String userName, String password, double score) {
        boolean success = userService.update(userID, userName, password, score);

        if (success) {
            return Result.SUCCESS("用户信息更新成功");
        } else {
            return Result.ERROR(StatusEnum.NO_DATA, "用户信息更新失败");
        }
    }

    @PostMapping("/search")
    public Result search(String userID, String startItem, String endItem) {

        int start = startItem == null ? -1 : Integer.parseInt(startItem);
        int end = endItem == null ? -1 : Integer.parseInt(endItem);

        List<User> users = userService.search(userID, start, end);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", users);
        resultMap.put("totalNum", userService.search(userID).size());

        return Result.SUCCESS(resultMap);
    }
//    @PostMapping("searchAll")
//    public Result searchAll() {
//        return Result.SUCCESS(userService.searchAll());
//    }

}
