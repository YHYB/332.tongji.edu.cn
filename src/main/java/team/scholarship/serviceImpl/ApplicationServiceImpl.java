package team.scholarship.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.scholarship.bean.Application;
import team.scholarship.mapper.ApplicationMapper;
import team.scholarship.service.ApplicationService;

import java.util.List;

/**
 * @ClassName ApplicationServiceImpl
 * @Description TODO
 * @Author Brian.Z
 * @Date 2020/12/20 18:10
 */
@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationMapper applicationMapper;

    @Override
    public List<Application> searchByUser(String userID) {
        return applicationMapper.searchByUser(userID);
    }

    @Override
    public boolean addApplication(String userID, String year, String scholarName, String reason) {

        try {
            applicationMapper.addApplication(userID, year, scholarName, reason);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
