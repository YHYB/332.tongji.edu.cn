package team.scholarship.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import team.scholarship.bean.User;

import java.util.List;

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

    void register(@Param("userID") String userID, @Param("name") String name,
                  @Param("password") String password);

    void update(@Param("userID") String userID, @Param("score") double score);

    void changePwd(@Param("userID") String userID, @Param("password") String password);

    List<User> searchByID(@Param("userID") String userID);

    List<User> searchAll();
}
