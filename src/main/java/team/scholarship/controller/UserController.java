package team.scholarship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    public String login(String userID, String password) {

        String pwd = userService.getPwd(userID).getPassword();

        if (pwd.equals(password)) {
            return "login success";
        } else {
            return "login failed";
        }
    }
}
