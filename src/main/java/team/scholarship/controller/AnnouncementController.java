package team.scholarship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.scholarship.bean.Announcement;
import team.scholarship.service.AnnouncementService;
import team.scholarship.service.ApplicationService;

import java.util.List;

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

    /**
     * used to search announcement by id
     * @param id announcement's id
     * @return an announcement whose id == #{id}
     */
    @PostMapping("/searchById")
    public Announcement searchById(int id){
        return announcementService.searchById(id);
    }

    /**
     * used to search announcement by key words in title
     * @param info which information user wants to search
     * @return a list of announcements whose title include the key word matching #{info}
     */
    @PostMapping("/searchByTitle")
    public List<Announcement> searchByTitle(String info){return announcementService.searchByTitle(info);}

    /**
     * make the readNum of the announcement whose id == #{id} increase one
     * @param id announcement's id
     */
    public void readNumIncrease(int id){
        announcementService.readNumIncrease(id);
    }

}
