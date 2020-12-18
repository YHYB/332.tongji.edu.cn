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

import com.learn.demo.springbootdemo.dao.CourseDao;
import com.learn.demo.springbootdemo.pojo.Course;


/**
 *
 * @description: 服务层
 * @author chenbin
 * @version: V1.0
 *
 */
@Service
public class CourseService {

	@Autowired
	private CourseDao courseDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Course> findAll() {
		return courseDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Course> findSearch(Map whereMap, int page, int size) {
		Specification<Course> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return courseDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Course> findSearch(Map whereMap) {
		Specification<Course> specification = createSpecification(whereMap);
		return courseDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Course findById(String id) {
		return courseDao.findById(id).orElse(null);
	}

	/**
	 * 增加
	 * @param course
	 */
	public void add(Course course) {
		course.setId( idWorker.nextId()+"" );
		courseDao.save(course);
	}

	/**
	 * 修改
	 * @param course
	 */
	public void update(Course course) {
		courseDao.save(course);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		courseDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Course> createSpecification(Map searchMap) {

		return new Specification<Course>() {

			@Override
			public Predicate toPredicate(Root<Course> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // 序列号
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                	predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 课程名称
                if (searchMap.get("course_name")!=null && !"".equals(searchMap.get("course_name"))) {
                	predicateList.add(cb.like(root.get("course_name").as(String.class), "%"+(String)searchMap.get("course_name")+"%"));
                }
                // 课程类别
                if (searchMap.get("course_category")!=null && !"".equals(searchMap.get("course_category"))) {
                	predicateList.add(cb.like(root.get("course_category").as(String.class), "%"+(String)searchMap.get("course_category")+"%"));
                }
                // 课程学分
                if (searchMap.get("course_credits")!=null && !"".equals(searchMap.get("course_credits"))) {
                	predicateList.add(cb.like(root.get("course_credits").as(String.class), "%"+(String)searchMap.get("course_credits")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
