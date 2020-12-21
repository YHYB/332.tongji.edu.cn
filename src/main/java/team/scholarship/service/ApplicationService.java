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

    List<Application> searchByUser(String userID);
}
