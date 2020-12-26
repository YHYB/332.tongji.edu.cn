package team.scholarship.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.scholarship.bean.Announcement;
import team.scholarship.mapper.AnnouncementMapper;
import team.scholarship.service.AnnouncementService;

import java.util.List;

/**
 * @author Kerr
 * @project scholarship manage system
 * @classname AnnouncementServiceImpl
 * @description TODO
 * @date 2020/12/20 20:55
 */
@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    @Autowired
    private AnnouncementMapper announcementMapper;

    /**
     * used to search announcement by id
     * @param id announcement's id
     * @return an announcement whose id == #{id}
     */
    @Override
    public Announcement searchById(int id) {
        readNumIncrease(id);
        return announcementMapper.searchById(id);
    }

    /**
     * make the readNum of the announcement whose id == #{id} increase one
     * @param id announcement's id
     */
    @Override
    public void readNumIncrease(int id) {
        // TODO 如果查到为空，会报错
        int num = announcementMapper.getReadNumById(id);
        num++;
        announcementMapper.setReadNumById(id,num);
    }

    /**
     * used to search announcement by key words in title
     * @param info which information user wants to search
     * @return a list of announcements whose title include the key word matching #{info}
     */
    @Override
    public List<Announcement> searchByTitle(String info) {
        return announcementMapper.searchByTitle(info);
    }

    @Override
    public boolean addAnnouncement(String date, String content, String title) {
        try{
            announcementMapper.addAnnouncement(date, content, title);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public List<Announcement> getAll() {
        return announcementMapper.getAll();
    }

    @Override
    public boolean deleteAnnouncement(int id) {
        try {
            announcementMapper.deleteAnnouncement(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
