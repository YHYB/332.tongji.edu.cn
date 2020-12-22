package team.scholarship.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import team.scholarship.bean.Application;
import team.scholarship.bean.Scholarship;
import team.scholarship.bean.User;

import java.util.List;

/**
 * @ClassName ApplicationMapper
 * @Description TODO
 * @Author Brian.Z
 * @Date 2020/12/20 18:03
 */
@Mapper
@Repository
public interface ApplicationMapper {

    List<Application> searchByUser(@Param("userID") String userID);

    void addApplication(@Param("userID") String userID, @Param("year") String year,
                               @Param("scholarName") String scholarName,
                               @Param("award") String award, @Param("reason") String reason);

    void updateInfo(@Param("userID") String userID, @Param("year") String year,
                           @Param("scholarName") String scholarName,
                           @Param("award") String award, @Param("reason") String reason);

    void updateScore(@Param("userID") String userID, @Param("year") String year,
                     @Param("scholarName") String scholarName,
                     @Param("score") double score);
}
