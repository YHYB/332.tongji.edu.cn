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

    boolean addApplication(String userID, String year, String scholarName,
                           String userName, double userGpa,
                           String award, boolean canAdjust, String reason);

    boolean updateInfo(String userID, String year, String scholarName, String award, String reason);

    boolean updateScore(String userID, String year, String scholarName, double score);
}
