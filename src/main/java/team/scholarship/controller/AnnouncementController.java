package team.scholarship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.scholarship.bean.Announcement;
import team.scholarship.service.AnnouncementService;
import team.scholarship.service.ApplicationService;

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

    @PostMapping("/searchById")
    public Announcement searchById(int id){
        return announcementService.searchById(id);
    }

    public void readNumIncrease(int id){
        announcementService.readNumIncrease(id);
    }

}
