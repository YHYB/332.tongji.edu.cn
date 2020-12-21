package team.scholarship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.scholarship.bean.Scholarship;
import team.scholarship.service.ScholarshipService;

/**
 * @author Kerr
 * @project scholarship management system
 * @classname ScholarshipController
 * @description TODO
 * @date 2020/12/21 15:46
 */
@CrossOrigin
@RestController
@RequestMapping("/scholarship")
public class ScholarshipController {

    @Autowired
    private ScholarshipService scholarshipService;

    @PostMapping("/searchByNameYear")
    public Scholarship searchByNameYear(String name, String year){
        return scholarshipService.searchByNameYear(name, year);
    }
}
