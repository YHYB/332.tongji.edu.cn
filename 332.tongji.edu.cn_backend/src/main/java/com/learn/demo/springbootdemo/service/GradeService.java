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

import com.learn.demo.springbootdemo.dao.GradeDao;
import com.learn.demo.springbootdemo.pojo.Grade;


/**
 *
 * @description: 服务层
 * @author chenbin
 * @version: V1.0
 *
 */
@Service
public class GradeService {

	@Autowired
	private GradeDao gradeDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Grade> findAll() {
		return gradeDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Grade> findSearch(Map whereMap, int page, int size) {
		Specification<Grade> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return gradeDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Grade> findSearch(Map whereMap) {
		Specification<Grade> specification = createSpecification(whereMap);
		return gradeDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Grade findById(String id) {
		return gradeDao.findById(id).orElse(null);
	}

	/**
	 * 增加
	 * @param grade
	 */
	public void add(Grade grade) {
		grade.setId( idWorker.nextId()+"" );
		gradeDao.save(grade);
	}

	/**
	 * 修改
	 * @param grade
	 */
	public void update(Grade grade) {
		gradeDao.save(grade);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		gradeDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Grade> createSpecification(Map searchMap) {

		return new Specification<Grade>() {

			@Override
			public Predicate toPredicate(Root<Grade> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // 序列号
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                	predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 年级名称
                if (searchMap.get("grade_name")!=null && !"".equals(searchMap.get("grade_name"))) {
                	predicateList.add(cb.like(root.get("grade_name").as(String.class), "%"+(String)searchMap.get("grade_name")+"%"));
                }
                // 年级主任
                if (searchMap.get("director")!=null && !"".equals(searchMap.get("director"))) {
                	predicateList.add(cb.like(root.get("director").as(String.class), "%"+(String)searchMap.get("director")+"%"));
                }
                // 联系方式
                if (searchMap.get("contact_infor")!=null && !"".equals(searchMap.get("contact_infor"))) {
                	predicateList.add(cb.like(root.get("contact_infor").as(String.class), "%"+(String)searchMap.get("contact_infor")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
