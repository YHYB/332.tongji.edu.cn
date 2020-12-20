package team.scholarship.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import team.scholarship.bean.User;

/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author Brian.Z
 * @Date 2020/12/20 1:38
 */
@Mapper
@Repository
public interface UserMapper {

    User getUserInfo(String userID);
}
