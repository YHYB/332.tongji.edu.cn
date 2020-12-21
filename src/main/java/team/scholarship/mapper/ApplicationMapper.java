package team.scholarship.mapper;

import org.apache.ibatis.annotations.Mapper;
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

    List<Application> searchByUser(String userID);

}
