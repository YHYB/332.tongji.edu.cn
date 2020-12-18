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
@Table(name="tb_classes")
public class Classes implements Serializable{

    /**
     * 序列号
     */
	@Id
	@Excel(name = "序列号",width = 25)
	private String id;

	
    /**
     * 班级名称
     */
    @Excel(name = "班级名称",width = 25)
	private String class_name;
    /**
     * 班主任
     */
    @Excel(name = "班主任",width = 25)
	private String teacher;
    /**
     * 工号
     */
    @Excel(name = "工号",width = 25)
	private String job_num;
    /**
     * 联系方式
     */
    @Excel(name = "联系方式",width = 25)
	private String contact_info;

	
	public String getId() {		
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getClass_name() {		
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}

	public String getTeacher() {		
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getJob_num() {		
		return job_num;
	}
	public void setJob_num(String job_num) {
		this.job_num = job_num;
	}

	public String getContact_info() {		
		return contact_info;
	}
	public void setContact_info(String contact_info) {
		this.contact_info = contact_info;
	}


	
}
