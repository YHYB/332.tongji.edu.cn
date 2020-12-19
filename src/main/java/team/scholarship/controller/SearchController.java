package team.scholarship.controller;

import team.scholarship.bean.PlayerBean;
import team.scholarship.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Brian.Z
 * @project chocolateFactory
 * @classname SearchController
 * @description TODO
 * @date 2020/12/16 16:23
 */
@Controller
public class SearchController {

    @Autowired
    PlayerService playerService;

    @RequestMapping("/search")
    public String show() {
        return "search";
    }

    @RequestMapping(value = "/searching11", method = RequestMethod.POST)
    public String searchPlayer(String name) {
        PlayerBean playerBean = playerService.searchPlayer(name);

        System.out.println("该用户为");

        if (playerBean != null) {
            System.out.println(playerBean.getName());
            return "success";
        } else {
            return "error";
        }
    }




}
