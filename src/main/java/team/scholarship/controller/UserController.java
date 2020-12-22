package team.scholarship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.scholarship.bean.User;
import team.scholarship.result.Result;
import team.scholarship.result.StatusEnum;
import team.scholarship.service.UserService;

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
    public Result<String> login(String userID, String password) {

        User user = userService.getUserInfo(userID);

        if (user == null) {
            return new Result<>(StatusEnum.NO_DATA);
        }

        String pwd = user.getPassword();
        String name = user.getName();

        if (pwd.equals(password)) {
            return new Result<>(StatusEnum.SUCCESS);
        } else {
            return new Result<>(StatusEnum.WRONG_PWD);
        }
    }

    @PostMapping("/info")
    public User getUserInfo(String userID) {
        return userService.getUserInfo(userID);
    }
}
