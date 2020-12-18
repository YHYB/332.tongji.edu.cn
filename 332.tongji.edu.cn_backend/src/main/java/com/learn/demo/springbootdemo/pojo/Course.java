package com.learn.demo.springbootdemo.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.*;

/**
 *
 * @description: 实体类
 * @author chenbin
 * @version: V1.0
 *
 */
@Entity
@Table(name="tb_course")
public class Course implements Serializable{

    /**
     * 序列号
     */
	@Id
	@Excel(name = "序列号",width = 25)
	private String id;

	
    /**
     * 课程名称
     */
    @Excel(name = "课程名称",width = 25)
	private String course_name;
    /**
     * 课程类别
     */
    @Excel(name = "课程类别",width = 25)
	private String course_category;
    /**
     * 课程学分
     */
    @Excel(name = "课程学分",width = 25)
	private String course_credits;

	
	public String getId() {		
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getCourse_name() {		
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public String getCourse_category() {		
		return course_category;
	}
	public void setCourse_category(String course_category) {
		this.course_category = course_category;
	}

	public String getCourse_credits() {		
		return course_credits;
	}
	public void setCourse_credits(String course_credits) {
		this.course_credits = course_credits;
	}


	
}
