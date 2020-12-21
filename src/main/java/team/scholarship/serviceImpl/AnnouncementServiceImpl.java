package team.scholarship.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.scholarship.bean.Announcement;
import team.scholarship.mapper.AnnouncementMapper;
import team.scholarship.service.AnnouncementService;

/**
 * @author Kerr
 * @project scholarship manage system
 * @classname AnnouncementServicempl
 * @description TODO
 * @date 2020/12/20 20:55
 */
@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    @Autowired
    private AnnouncementMapper announcementMapper;

    @Override
    public Announcement searchById(int id) {
        readNumIncrease(id);
        return announcementMapper.searchById(id);
    }

    @Override
    public void readNumIncrease(int id) {
        // TODO 如果查到为空，会报错
        int num = announcementMapper.getReadNumById(id);
        num++;
        announcementMapper.setReadNumById(id,num);
    }
}
