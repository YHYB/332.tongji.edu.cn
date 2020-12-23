package team.scholarship.service;

import org.springframework.stereotype.Service;
import team.scholarship.bean.Announcement;

import java.util.List;

@Service
public interface AnnouncementService {
    Announcement searchById(int id);

    void readNumIncrease(int id);

    List <Announcement> searchByTitle(String info);

    void addAnnouncement(String date, String content, String title);

    List<Announcement> getAll();
}
