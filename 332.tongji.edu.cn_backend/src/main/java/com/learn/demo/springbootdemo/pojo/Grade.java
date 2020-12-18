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
@Table(name="tb_grade")
public class Grade implements Serializable{

    /**
     * 序列号
     */
	@Id
	@Excel(name = "序列号",width = 25)
	private String id;

	
    /**
     * 年级名称
     */
    @Excel(name = "年级名称",width = 25)
	private String grade_name;
    /**
     * 年级主任
     */
    @Excel(name = "年级主任",width = 25)
	private String director;
    /**
     * 联系方式
     */
    @Excel(name = "联系方式",width = 25)
	private String contact_infor;

	
	public String getId() {		
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getGrade_name() {		
		return grade_name;
	}
	public void setGrade_name(String grade_name) {
		this.grade_name = grade_name;
	}

	public String getDirector() {		
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}

	public String getContact_infor() {		
		return contact_infor;
	}
	public void setContact_infor(String contact_infor) {
		this.contact_infor = contact_infor;
	}


	
}
