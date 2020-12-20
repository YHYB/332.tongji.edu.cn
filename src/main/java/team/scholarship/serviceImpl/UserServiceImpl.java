package team.scholarship.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.scholarship.bean.User;
import team.scholarship.mapper.UserMapper;
import team.scholarship.service.UserService;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author Brian.Z
 * @Date 2020/12/20 1:45
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getPwd(String usrId) {
        return userMapper.getPwd(usrId);
    }
}
