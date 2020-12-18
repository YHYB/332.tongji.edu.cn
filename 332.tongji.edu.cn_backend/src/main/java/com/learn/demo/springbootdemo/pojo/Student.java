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
@Table(name="tb_student")
public class Student implements Serializable{

    /**
     * 序列号
     */
	@Id
	@Excel(name = "序列号",width = 25)
	private String id;

	
    /**
     * 学号
     */
    @Excel(name = "学号",width = 25)
	private String student_num;
    /**
     * 姓名
     */
    @Excel(name = "姓名",width = 25)
	private String name;
    /**
     * 性别
     */
    @Excel(name = "性别",width = 25)
	private String gender;
    /**
     * 联系方式
     */
    @Excel(name = "联系方式",width = 25)
	private String contact_infor;
    /**
     * 班级
     */
    @Excel(name = "班级",width = 25)
	private String class_info;

	
	public String getId() {		
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getStudent_num() {		
		return student_num;
	}
	public void setStudent_num(String student_num) {
		this.student_num = student_num;
	}

	public String getName() {		
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {		
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getContact_infor() {		
		return contact_infor;
	}
	public void setContact_infor(String contact_infor) {
		this.contact_infor = contact_infor;
	}

	public String getClass_info() {		
		return class_info;
	}
	public void setClass_info(String class_info) {
		this.class_info = class_info;
	}


	
}
