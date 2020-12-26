package team.scholarship.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import team.scholarship.bean.Application;

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

    List<Application> searchAll();

    List<Application> searchByUser(@Param("userID") String userID);

    List<Application> searchByYear(@Param("year") String year);

    List<Application> searchByScholar(@Param("scholarName") String scholarName);

    List<Application> searchByUserAndYear(@Param("userID") String userID,
                                          @Param("year") String year);

    List<Application> searchByUserAndScholar(@Param("userID") String userID,
                                             @Param("scholarName") String scholarName);

    List<Application> searchByYearAndScholar(@Param("year") String year,
                                             @Param("scholarName") String scholarName);

    List<Application> search(@Param("userID") String userID, @Param("year") String year,
                       @Param("scholarName") String scholarName);

    void addApplication(@Param("userID") String userID, @Param("year") String year,
                               @Param("scholarName") String scholarName,
                               @Param("userName") String userName,
                               @Param("userGpa") double userGpa,
                               @Param("award") String award, @Param("canAdjust") boolean canAdjust,
                               @Param("reason") String reason);

    void deleteApplication(@Param("userID") String userID, @Param("year") String year,
                           @Param("scholarName") String scholarName);

    void updateInfo(@Param("userID") String userID, @Param("year") String year,
                           @Param("scholarName") String scholarName,
                           @Param("userGpa") double userGpa,
                           @Param("award") String award, @Param("canAdjust") boolean canAdjust,
                           @Param("reason") String reason);

    void updateScore(@Param("userID") String userID, @Param("year") String year,
                     @Param("scholarName") String scholarName,
                     @Param("score") double score);

    void updateStatus(@Param("userID") String userID, @Param("year") String year,
                      @Param("scholarName") String scholarName,
                      @Param("status") String status);

    List<Application> getAllPassed();
}
