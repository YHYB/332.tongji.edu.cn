package team.scholarship.service;

import org.springframework.stereotype.Service;
import team.scholarship.bean.Announcement;

@Service
public interface AnnouncementService {
    Announcement searchById(int id);

    void readNumIncrease(int id);
}
