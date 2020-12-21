package team.scholarship.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import team.scholarship.bean.Scholarship;

@Mapper
@Repository
public interface ScholarshipMapper {
    Scholarship searchByNameYear(String name, String year);
}
