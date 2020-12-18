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

import com.learn.demo.springbootdemo.dao.ClassesDao;
import com.learn.demo.springbootdemo.pojo.Classes;


/**
 *
 * @description: 服务层
 * @author chenbin
 * @version: V1.0
 *
 */
@Service
public class ClassesService {

	@Autowired
	private ClassesDao classesDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Classes> findAll() {
		return classesDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Classes> findSearch(Map whereMap, int page, int size) {
		Specification<Classes> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return classesDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Classes> findSearch(Map whereMap) {
		Specification<Classes> specification = createSpecification(whereMap);
		return classesDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Classes findById(String id) {
		return classesDao.findById(id).orElse(null);
	}

	/**
	 * 增加
	 * @param classes
	 */
	public void add(Classes classes) {
		classes.setId( idWorker.nextId()+"" );
		classesDao.save(classes);
	}

	/**
	 * 修改
	 * @param classes
	 */
	public void update(Classes classes) {
		classesDao.save(classes);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		classesDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Classes> createSpecification(Map searchMap) {

		return new Specification<Classes>() {

			@Override
			public Predicate toPredicate(Root<Classes> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // 序列号
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                	predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 班级名称
                if (searchMap.get("class_name")!=null && !"".equals(searchMap.get("class_name"))) {
                	predicateList.add(cb.like(root.get("class_name").as(String.class), "%"+(String)searchMap.get("class_name")+"%"));
                }
                // 班主任
                if (searchMap.get("teacher")!=null && !"".equals(searchMap.get("teacher"))) {
                	predicateList.add(cb.like(root.get("teacher").as(String.class), "%"+(String)searchMap.get("teacher")+"%"));
                }
                // 工号
                if (searchMap.get("job_num")!=null && !"".equals(searchMap.get("job_num"))) {
                	predicateList.add(cb.like(root.get("job_num").as(String.class), "%"+(String)searchMap.get("job_num")+"%"));
                }
                // 联系方式
                if (searchMap.get("contact_info")!=null && !"".equals(searchMap.get("contact_info"))) {
                	predicateList.add(cb.like(root.get("contact_info").as(String.class), "%"+(String)searchMap.get("contact_info")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
