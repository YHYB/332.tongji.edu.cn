package com.learn.demo.springbootdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.learn.demo.springbootdemo.pojo.Teacher;

/**
 *
 * @description: 数据访问接口
 * @author chenbin
 * @version: V1.0
 *
 */
public interface TeacherDao extends JpaRepository<Teacher,String>,JpaSpecificationExecutor<Teacher>{
	
}
