package team.scholarship.serviceImpl;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.scholarship.bean.Scholarship;
import team.scholarship.mapper.ScholarshipMapper;
import team.scholarship.service.ScholarshipService;

/**
 * @author Kerr
 * @project scholarship managetment system
 * @classname ScholarshipServiceImpl
 * @description TODO
 * @date 2020/12/21 15:47
 */
@Service
public class ScholarshipServiceImpl implements ScholarshipService {

    @Autowired
    private ScholarshipMapper scholarshipMapper;
    @Override
    public Scholarship searchByNameYear(@Param("name") String name, @Param("year") String year) {
        return scholarshipMapper.searchByNameYear(name, year);
    }
}
