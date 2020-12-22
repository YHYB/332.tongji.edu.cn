package team.scholarship.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.scholarship.bean.Application;
import team.scholarship.result.Result;
import team.scholarship.result.StatusEnum;
import team.scholarship.service.ApplicationService;

import java.util.List;

/**
 * @ClassName AnnounceController
 * @Description TODO
 * @Author Brian.Z
 * @Date 2020/12/20 17:57
 */
@CrossOrigin
@RestController
@RequestMapping("/apply")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/searchByUser")
    public Result searchByUser(String userID) {
        List<Application> data = applicationService.searchByUser(userID);
        System.out.println(data);

        if (data.size() == 0) {
            // 查询成功，但没有数据
            return new Result(StatusEnum.NO_DATA, data);
        } else {
            // 查询成功
            return new Result(StatusEnum.SUCCESS, data);
        }
    }

    @PostMapping("/add")
    public Result addApplication(String userID, String year, String scholarName,
                                             String userName, double userGpa,
                                             String award, boolean canAdjust, String reason) {
        boolean add = applicationService.addApplication(userID, year, scholarName, userName, userGpa, award,
                                                        canAdjust, reason);

        if (add) {
            return new Result(StatusEnum.SUCCESS, "申请成功");
        } else {
            return new Result(StatusEnum.DUPLICATE_PK,"已申请过该奖学金！");
        }
    }

    @PostMapping("/updateInfo")
    public Result updateInfo(String userID, String year, String scholarName, String award, String reason) {
        boolean update = applicationService.updateInfo(userID, year, scholarName, award, reason);

        if (update) {
            return new Result(StatusEnum.SUCCESS, "更新成功");
        } else {
            return new Result(StatusEnum.NO_DATA,"更新失败");
        }
    }

    @PostMapping("/updateScore")
    public Result updateScore(String userID, String year, String scholarName, double score) {
        boolean update = applicationService.updateScore(userID, year, scholarName, score);

        if (update) {
            return new Result(StatusEnum.SUCCESS, "更新成功");
        } else {
            return new Result(StatusEnum.NO_DATA,"更新失败");
        }
    }
}
