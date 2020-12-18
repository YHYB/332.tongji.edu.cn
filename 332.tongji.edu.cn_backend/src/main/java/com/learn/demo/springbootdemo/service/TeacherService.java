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

import com.learn.demo.springbootdemo.dao.TeacherDao;
import com.learn.demo.springbootdemo.pojo.Teacher;


/**
 *
 * @description: 服务层
 * @author chenbin
 * @version: V1.0
 *
 */
@Service
public class TeacherService {

	@Autowired
	private TeacherDao teacherDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Teacher> findAll() {
		return teacherDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Teacher> findSearch(Map whereMap, int page, int size) {
		Specification<Teacher> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return teacherDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Teacher> findSearch(Map whereMap) {
		Specification<Teacher> specification = createSpecification(whereMap);
		return teacherDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Teacher findById(String id) {
		return teacherDao.findById(id).orElse(null);
	}

	/**
	 * 增加
	 * @param teacher
	 */
	public void add(Teacher teacher) {
		teacher.setId( idWorker.nextId()+"" );
		teacherDao.save(teacher);
	}

	/**
	 * 修改
	 * @param teacher
	 */
	public void update(Teacher teacher) {
		teacherDao.save(teacher);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		teacherDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Teacher> createSpecification(Map searchMap) {

		return new Specification<Teacher>() {

			@Override
			public Predicate toPredicate(Root<Teacher> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // 序列号
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                	predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 工号
                if (searchMap.get("job_num")!=null && !"".equals(searchMap.get("job_num"))) {
                	predicateList.add(cb.like(root.get("job_num").as(String.class), "%"+(String)searchMap.get("job_num")+"%"));
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
                // 职位
                if (searchMap.get("position")!=null && !"".equals(searchMap.get("position"))) {
                	predicateList.add(cb.like(root.get("position").as(String.class), "%"+(String)searchMap.get("position")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
