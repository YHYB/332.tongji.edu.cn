package team.scholarship.serviceImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.scholarship.bean.Application;
import team.scholarship.bean.User;
import team.scholarship.mapper.UserMapper;
import team.scholarship.service.UserService;
import team.scholarship.util.JwtUtil;

import java.util.List;

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
    public User getUserInfo(String userID) {

        User user = new User();
        try {
            user = userMapper.getUserInfo(userID);
        } catch (Exception e) {
            return null;
        }
        return user;
    }

    @Override
    public User login(String userID, String password) {

        User user = new User();
        try {
            user = userMapper.getUserInfo(userID);
            if (!user.getPassword().equals(password)) {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
        return user;
    }

    @Override
    public boolean register(String userID, String userName, String password) {
        try {
            userMapper.register(userID, userName, password);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public User getInfo(String token) {
        JwtUtil jwtUtil = new JwtUtil();
        Claims claims = jwtUtil.parseJWT(token);
        String userID = claims.getId();
        return userMapper.getUserInfo(userID);
    }

    @Override
    public boolean update(String userID, String userName, String password, double score) {
//        try {
//            userMapper.update(userID, userName, password, score);
//        } catch (Exception e) {
//            return false;
//        }
        userMapper.update(userID, userName, password, score);
        return true;
    }

    @Override
    public List<User> search(String userID, int startItem, int endItem) {
        List<User> result = this.search(userID);

        endItem = Math.min(endItem, result.size());

        if (startItem != -1 && endItem != -1) {
            return result.subList(startItem, endItem);
        }
        return result;
    }

    @Override
    public List<User> search(String _id) {
        String userID = _id == null ? "" : _id;

        if (userID.equals("")) {
            return userMapper.searchAll();
        }
        return userMapper.searchByID(userID);
    }

}
