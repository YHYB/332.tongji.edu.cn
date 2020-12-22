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

    @PostMapping("/searchAll")
    public Result searchAll() {
        List<Application> data = applicationService.searchAll();
        System.out.println(data);

        if (data.size() == 0) {
            return Result.ERROR(StatusEnum.NO_DATA, "没有查询到任何申请");
        } else {
            return Result.SUCCESS(data);
        }
    }

    @PostMapping("/searchByUser")
    public Result searchByUser(String userID) {
        System.out.println("输入userID:" + userID);
        List<Application> data = applicationService.searchByUser(userID);
        System.out.println(data);

        if (data.size() == 0) {
            return Result.ERROR(StatusEnum.NO_DATA, "没有查询到任何申请");
        } else {
            return Result.SUCCESS(data);
        }
    }

    @PostMapping("/add")
    public Result addApplication(String userID, String year, String scholarName,
                                 String userName, double userGpa,
                                 String award, boolean canAdjust, String reason) {
        boolean add = applicationService.addApplication(userID, year, scholarName, userName, userGpa, award,
                canAdjust, reason);

        if (add) {
            return Result.SUCCESS("申请成功");
        } else {
            return Result.ERROR(StatusEnum.DUPLICATE_PK, "已申请该奖学金");
        }
    }

    @PostMapping("/updateInfo")
    public Result updateInfo(String userID, String year, String scholarName, String award, String reason) {
        boolean update = applicationService.updateInfo(userID, year, scholarName, award, reason);

        if (update) {
            return Result.SUCCESS("更新成功");
        } else {
            return Result.ERROR(StatusEnum.NO_DATA, "更新失败");
        }
    }

    @PostMapping("/updateScore")
    public Result updateScore(String userID, String year, String scholarName, double score) {
        boolean update = applicationService.updateScore(userID, year, scholarName, score);

        if (update) {
            return Result.SUCCESS("更新成功");
        } else {
            return Result.ERROR(StatusEnum.NO_DATA, "更新失败");
        }
    }
}
