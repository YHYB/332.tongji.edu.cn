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

    public List<Application> search(String _id, String _year, String _name) {

        String userID = _id == null ? "" : _id;
        String year = _year == null ? "" : _year;
        String scholarName = _name == null ? "" :_name;

        if (userID.equals("") && year.equals("") && scholarName.equals("")) {
            return applicationMapper.searchAll();
        }
        if (userID.equals("") && year.equals("")) {
            return applicationMapper.searchByScholar(scholarName);
        }
        if (userID.equals("") && scholarName.equals("")) {
            return applicationMapper.searchByYear(year);
        }
        if (year.equals("") && scholarName.equals("")) {
            return applicationMapper.searchByUser(userID);
        }
        if (userID.equals("")) {
            return applicationMapper.searchByYearAndScholar(year, scholarName);
        }
        if (year.equals("")) {
            return applicationMapper.searchByUserAndScholar(userID, scholarName);
        }
        if (scholarName.equals("")) {
            return applicationMapper.searchByUserAndYear(userID, year);
        }
        return applicationMapper.search(userID, year, scholarName);
    }

    @Override
    public List<Application> searchAdmin(String userID, String year, String scholarName, int startItem, int endItem) {

        List<Application> applications = this.search(userID, year, scholarName);
        applications.removeIf(application -> !application.getStatus().equals("待审核"));

        endItem = Math.min(endItem, applications.size());

        if (startItem != -1 && endItem != -1) {
            return applications.subList(startItem, endItem);
        }
        return applications;
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
