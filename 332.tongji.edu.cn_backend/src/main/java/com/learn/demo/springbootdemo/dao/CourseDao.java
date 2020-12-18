package com.learn.demo.springbootdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.learn.demo.springbootdemo.pojo.Course;

/**
 *
 * @description: 数据访问接口
 * @author chenbin
 * @version: V1.0
 *
 */
public interface CourseDao extends JpaRepository<Course,String>,JpaSpecificationExecutor<Course>{
	
}
