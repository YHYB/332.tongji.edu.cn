package team.scholarship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.scholarship.bean.Announcement;
import team.scholarship.bean.Application;
import team.scholarship.result.Result;
import team.scholarship.result.StatusEnum;
import team.scholarship.service.AnnouncementService;
import team.scholarship.service.ApplicationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kerr
 * @project scholar management system
 * @classname AnnouncementController
 * @description TODO
 * @date 2020/12/20 20:51
 */
@CrossOrigin
@RestController
@RequestMapping("/announcement")
public class AnnouncementController {
    @Autowired
    private AnnouncementService announcementService;

    @Autowired
    private ApplicationService applicationService;

    /**
     * add a new announcement into database
     *
     * @param date    template: yyyymmdd
     * @param content
     * @param title
     * @return
     */
    @PostMapping("/addAnnouncement")
    public Result addAnnouncement(String date, String content, String title) {
        boolean rlt = announcementService.addAnnouncement(date, content, title);
        if(rlt){
            return Result.SUCCESS();
        }else{
            return Result.ERROR(StatusEnum.DUPLICATE_PK);
        }
    }


    @PostMapping("/deleteAnnouncement")
    public Result deleteAnnouncement(int id){
        boolean rlt = announcementService.deleteAnnouncement(id);
        if(rlt){
            return Result.SUCCESS();
        }else{
            return Result.ERROR(StatusEnum.NO_DATA);
        }
    }
    /**
     * used to search announcement by id
     *
     * @param id
     * @return an announcement whose id == #{id}
     */
    @PostMapping("/searchById")
    public Result searchById(int id) {
        Announcement data = announcementService.searchById(id);
        if (data == null) {
            return Result.ERROR(StatusEnum.NO_DATA);
        } else {
            return Result.SUCCESS(data);
        }
    }

    /**
     * used to search announcement by key words in title
     *
     * @param info which information user wants to search
     * @return a list of announcements whose title include the key word matching #{info}
     */
    @PostMapping("/searchByTitle")
    public List<Announcement> searchByTitle(String info) {
        return announcementService.searchByTitle(info);
    }

    /**
     * make the readNum of the announcement whose id == #{id} increase one
     *
     * @param id announcement's id
     */
    public void readNumIncrease(int id) {
        announcementService.readNumIncrease(id);
    }


    @PostMapping("/getAll")
    public Result getAll() {
        List<Announcement> data = announcementService.getAll();

        if (data == null) {
            return Result.ERROR(StatusEnum.NO_DATA);
        } else {
            return Result.SUCCESS(data);
        }
    }

    @PostMapping("/publish")
    public Result publish() {
        List<Application> data = applicationService.getAllPassed();
        if (data == null) {
            return Result.ERROR(StatusEnum.NO_DATA);
        } else {
            Date day = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            java.util.Date date = new java.util.Date();
            String tmp = new String();
            for (Application d : data) {
                tmp += d.getUsr_id() + "        " + d.getUsr_name() + "     " + d.getScholar_name() + "\n";
            }
            announcementService.addAnnouncement(df.format(date), tmp, "奖学金获奖公示");
            return Result.SUCCESS();
        }
    }

    @PostMapping("/search")
    public Result search(int startItem, int endItem) {
        Map<String, Object> resultMap = new HashMap<>();

        List<Announcement> announcements = announcementService.getAll();
        int totalNum = announcementService.getAll().size();
        int end = Math.min(endItem, totalNum);

        if (announcements == null || announcements.size() == 0) {
            return Result.ERROR(StatusEnum.NO_DATA);
        } else {
            resultMap.put("data", announcements.subList(startItem - 1, end));
            resultMap.put("totalNum", announcementService.getAll().size());
            return Result.SUCCESS(resultMap);
        }
    }
}
