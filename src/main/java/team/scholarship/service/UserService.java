package team.scholarship.service;

import org.springframework.stereotype.Service;
import team.scholarship.bean.User;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author Brian.Z
 * @Date 2020/12/20 1:44
 */
@Service
public interface UserService {

    User getPwd(String usrId);
}
