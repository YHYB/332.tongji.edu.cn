package com.learn.demo.springbootdemo;

import com.learn.demo.springbootdemo.pojo.*;
import com.learn.demo.springbootdemo.service.*;
import com.learn.demo.springbootdemo.util.IdWorker;
import com.learn.demo.springbootdemo.util.RandomDataUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootDemoApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private IdWorker idWorker;

    @Test
    public void contextLoads() {
        for(int i = 0;i<1000;i++){
            User user = new User();
            user.setId(idWorker.nextId()+"");
            user.setUsername(RandomDataUtil.randomEnString(7));
            user.setPassword(RandomDataUtil.randomNumAndEn(6));
            userService.add(user);
        }
    }

    @Test
    public void student() {
        for(int i = 0;i<1000;i++){
            Student student = new Student();
            student.setId(idWorker.nextId()+"");
            String[] split = RandomDataUtil.randomChName().split("-");
            student.setName(split[0]);
            student.setGender(split[1]);
            student.setContact_infor(RandomDataUtil.randomPhone());
            student.setStudent_num(String.valueOf(RandomDataUtil.randomNumber(20160190,20200190)));
            student.setClass_info("软件"+RandomDataUtil.randomNumber(182601,182610)+"班");
            studentService.add(student);
            System.out.println(i);
        }
    }

    @Autowired
    private TeacherService teacherService;
    @Test
    public void teacher() {
        String[] course = new String[]{"操作系统","数据结构","计算机网络","JAVA程序设计"};
        for(int i = 0;i<1000;i++){
            Teacher student = new Teacher();
            student.setId(idWorker.nextId()+"");
            String[] split = RandomDataUtil.randomChName().split("-");
            student.setName(split[0]);
            student.setGender(split[1]);
            student.setContact_infor(RandomDataUtil.randomPhone());
            student.setJob_num(String.valueOf(RandomDataUtil.randomNumber(20160190,20200190)));
            int x = RandomDataUtil.randomNumber(0,3);
            student.setPosition(course[x]);
            teacherService.add(student);
            System.out.println(i);
        }
    }

    @Autowired
    private ClassesService classesService;

    @Test
    public void test(){
        for(int i =0;i<20;i++){
            Classes classes = new Classes();
            classes.setId(idWorker.nextId()+"");
            classes.setClass_name("软件工程"+RandomDataUtil.randomNumber(162601,202609));
            classes.setContact_info(RandomDataUtil.randomPhone());
            classes.setJob_num(""+RandomDataUtil.randomNumber(16002111,20001112));
            classes.setTeacher(RandomDataUtil.randomChName().split("-")[0]);
            classesService.add(classes);
        }
    }

    @Autowired
    private GradeService gradeService;

    @Test
    public void test2 () {
        for (int i = 0;i<20;i++){
            Grade grade = new Grade();
            grade.setId(idWorker.nextId()+"");
            grade.setContact_infor(RandomDataUtil.randomPhone());
            grade.setDirector(RandomDataUtil.randomChName().split("-")[0]);
            grade.setGrade_name(RandomDataUtil.randomNumber(16,20)+"届");
            gradeService.add(grade);
        }
    }
}
