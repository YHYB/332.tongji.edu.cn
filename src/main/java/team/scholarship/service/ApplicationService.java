package team.scholarship.service;

import org.springframework.stereotype.Service;
import team.scholarship.bean.Application;

import java.util.List;

/**
 * @ClassName ApplicationService
 * @Description TODO
 * @Author Brian.Z
 * @Date 2020/12/20 18:08
 */
@Service
public interface ApplicationService {

    List<Application> searchAll();

    List<Application> searchByUser(String userID);

    List<Application> search(String userID, String year, String scholarName,
                       int startItem, int endItem);

    List<Application> search(String userID, String year, String scholarName);

    boolean addApplication(String userID, String year, String scholarName,
                           String userName, double userGpa,
                           String award, boolean canAdjust, String reason);

    boolean deleteApplication(String userID, String year, String scholarName);

    boolean updateInfo(String userID, String year, String scholarName,
                       double userGpa, String award, boolean canAdjust, String reason);

    boolean updateScore(String userID, String year, String scholarName, double score);

    boolean updateStatus(String userID, String year, String scholarName, String status);
}
