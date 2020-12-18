package com.learn.demo.springbootdemo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.learn.demo.springbootdemo.util.IdWorker;

import com.learn.demo.springbootdemo.dao.StudentDao;
import com.learn.demo.springbootdemo.pojo.Student;


/**
 *
 * @description: 服务层
 * @author chenbin
 * @version: V1.0
 *
 */
@Service
public class StudentService {

	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Student> findAll() {
		return studentDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Student> findSearch(Map whereMap, int page, int size) {
		Specification<Student> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return studentDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Student> findSearch(Map whereMap) {
		Specification<Student> specification = createSpecification(whereMap);
		return studentDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Student findById(String id) {
		return studentDao.findById(id).orElse(null);
	}

	/**
	 * 增加
	 * @param student
	 */
	public void add(Student student) {
		student.setId( idWorker.nextId()+"" );
		studentDao.save(student);
	}

	/**
	 * 修改
	 * @param student
	 */
	public void update(Student student) {
		studentDao.save(student);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		studentDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Student> createSpecification(Map searchMap) {

		return new Specification<Student>() {

			@Override
			public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // 序列号
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                	predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 学号
                if (searchMap.get("student_num")!=null && !"".equals(searchMap.get("student_num"))) {
                	predicateList.add(cb.like(root.get("student_num").as(String.class), "%"+(String)searchMap.get("student_num")+"%"));
                }
                // 姓名
                if (searchMap.get("name")!=null && !"".equals(searchMap.get("name"))) {
                	predicateList.add(cb.like(root.get("name").as(String.class), "%"+(String)searchMap.get("name")+"%"));
                }
                // 性别
                if (searchMap.get("gender")!=null && !"".equals(searchMap.get("gender"))) {
                	predicateList.add(cb.like(root.get("gender").as(String.class), "%"+(String)searchMap.get("gender")+"%"));
                }
                // 联系方式
                if (searchMap.get("contact_infor")!=null && !"".equals(searchMap.get("contact_infor"))) {
                	predicateList.add(cb.like(root.get("contact_infor").as(String.class), "%"+(String)searchMap.get("contact_infor")+"%"));
                }
                // 班级
                if (searchMap.get("class_info")!=null && !"".equals(searchMap.get("class_info"))) {
                	predicateList.add(cb.like(root.get("class_info").as(String.class), "%"+(String)searchMap.get("class_info")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
