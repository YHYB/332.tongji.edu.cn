package team.scholarship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.scholarship.bean.Application;
import team.scholarship.result.Result;
import team.scholarship.result.StatusEnum;
import team.scholarship.service.ApplicationService;

import java.util.*;

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

    @PostMapping("/search")
    public Result search(String userID, String year, String scholarName,
                         String startItem, String endItem) {
//        System.out.println("userID: " + userID);
//        System.out.println("year: " + year);
//        System.out.println("scholarship: " + scholarName);
//        System.out.println("startItem: " + startItem);

        int start = startItem == null ? -1 : Integer.parseInt(startItem);
        int end = endItem == null ? -1 : Integer.parseInt(endItem);

        List<Application> application = applicationService.search(userID, year, scholarName,
                start - 1, end);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", application);
        resultMap.put("totalNum", applicationService.search(userID, year, scholarName).size());

        if (application == null) {
            return Result.ERROR(StatusEnum.NO_DATA);
        } else {
            return Result.SUCCESS(resultMap);
        }
    }

    @PostMapping("/admin/search")
    public Result searchForAdmin(String userID, String year, String scholarName,
                                 String startItem, String endItem) {

        int start = startItem == null ? -1 : Integer.parseInt(startItem);
        int end = endItem == null ? -1 : Integer.parseInt(endItem);

        List<Application> applications = applicationService.searchAdmin(userID, year, scholarName,
                start - 1, end);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", applications);
        resultMap.put("totalNum", applications.size());

        return Result.SUCCESS(resultMap);
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

    @PostMapping("/delete")
    public Result deleteApplication(String userID, String year, String scholarName) {

        boolean delete = applicationService.deleteApplication(userID, year, scholarName);

        if (delete) {
            return Result.SUCCESS("删除成功");
        } else {
            return Result.ERROR(StatusEnum.NO_DATA, "删除失败");
        }
    }

    @PostMapping("/updateInfo")
    public Result updateInfo(String userID, String year, String scholarName,
                             double userGpa, String award,
                             boolean canAdjust, String reason) {
        boolean update = applicationService.updateInfo(userID, year, scholarName,
                userGpa, award, canAdjust, reason);
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

    @PostMapping("/updateStatus")
    public Result updateStatus(String userID, String year, String scholarName, String status) {
        boolean update = applicationService.updateStatus(userID, year, scholarName, status);

        if (update) {
            return Result.SUCCESS("更新成功");
        } else {
            return Result.ERROR(StatusEnum.NO_DATA, "更新失败");
        }
    }

    @PostMapping("/getAllPassed")
    public Result getAllPassed() {
        List<Application> data = applicationService.getAllPassed();
        if (data == null) {
            return Result.ERROR(StatusEnum.NO_DATA);
        } else {
            return Result.SUCCESS(data);
        }
    }

}
