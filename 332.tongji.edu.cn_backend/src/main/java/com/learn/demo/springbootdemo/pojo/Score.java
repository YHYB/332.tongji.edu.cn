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
@Table(name="tb_score")
public class Score implements Serializable{

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
     * 课程编号
     */
    @Excel(name = "课程编号",width = 25)
	private String course_num;
    /**
     * 成绩
     */
    @Excel(name = "成绩",width = 25)
	private String score;

	
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

	public String getCourse_num() {		
		return course_num;
	}
	public void setCourse_num(String course_num) {
		this.course_num = course_num;
	}

	public String getScore() {		
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}


	
}
