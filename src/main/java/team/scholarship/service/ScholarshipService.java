package team.scholarship.service;

import org.springframework.stereotype.Service;
import team.scholarship.bean.Scholarship;

@Service
public interface ScholarshipService {
    Scholarship searchByNameYear(String name, String year);
}
