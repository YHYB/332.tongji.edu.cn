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
@Table(name="t_user")
public class User implements Serializable{

    /**
     * 序列号
     */
	@Id
	@Excel(name = "序列号",width = 25)
	private String id;


    /**
     * 用户名
     */
	@Excel(name = "用户名",width = 25)
	private String username;
    /**
     * 密码
     */
	@Excel(name = "密码",width = 25)
	private String password;


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
