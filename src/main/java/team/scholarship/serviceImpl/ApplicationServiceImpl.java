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
    public List<Application> searchAll() {
        return applicationMapper.searchAll();
    }

    @Override
    public List<Application> searchByUser(String userID) {
        return applicationMapper.searchByUser(userID);
    }

    @Override
    public List<Application> search(String userID, String year, String scholarName, int startItem, int endItem) {

        List<Application> result = this.search(userID, year, scholarName);

        endItem = Math.min(endItem, result.size());

        if (startItem != -1 && endItem != -1) {
            return result.subList(startItem, endItem);
        }
        return result;
    }

    private List<Application> search(String userID, String year, String scholarName) {
        if (userID == null && year == null && scholarName == null) {
            return applicationMapper.searchAll();
        }
        if (userID == null && year == null) {
            return applicationMapper.searchByScholar(scholarName);
        }
        if (userID == null && scholarName == null) {
            return applicationMapper.searchByYear(year);
        }
        if (year == null && scholarName == null) {
            return applicationMapper.searchByUser(userID);
        }
        if (userID == null) {
            return applicationMapper.searchByYearAndScholar(year, scholarName);
        }
        if (year == null) {
            return applicationMapper.searchByUserAndScholar(userID, scholarName);
        }
        if(scholarName == null) {
            return applicationMapper.searchByUserAndYear(userID, year);
        }
        return applicationMapper.search(userID, year, scholarName);
    }


    @Override
    public boolean addApplication(String userID, String year, String scholarName,
                                  String userName, double userGpa,
                                  String award, boolean canAdjust, String reason) {

        try {
            applicationMapper.addApplication(userID, year, scholarName, userName, userGpa, award, canAdjust, reason);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteApplication(String userID, String year, String scholarName) {

        try {
            applicationMapper.deleteApplication(userID, year, scholarName);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateInfo(String userID, String year, String scholarName,
                              double userGpa, String award, boolean canAdjust, String reason) {

        try {
            applicationMapper.updateInfo(userID, year, scholarName, userGpa, award,
                    canAdjust, reason);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateScore(String userID, String year, String scholarName, double score) {

        try {
            applicationMapper.updateScore(userID, year, scholarName, score);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateStatus(String userID, String year, String scholarName, String status) {
        try {
            applicationMapper.updateStatus(userID, year, scholarName, status);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
