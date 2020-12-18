package com.learn.demo.springbootdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.learn.demo.springbootdemo.pojo.Classes;

/**
 *
 * @description: 数据访问接口
 * @author chenbin
 * @version: V1.0
 *
 */
public interface ClassesDao extends JpaRepository<Classes,String>,JpaSpecificationExecutor<Classes>{
	
}
