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

import com.learn.demo.springbootdemo.dao.ScoreDao;
import com.learn.demo.springbootdemo.pojo.Score;


/**
 *
 * @description: 服务层
 * @author chenbin
 * @version: V1.0
 *
 */
@Service
public class ScoreService {

	@Autowired
	private ScoreDao scoreDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Score> findAll() {
		return scoreDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Score> findSearch(Map whereMap, int page, int size) {
		Specification<Score> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return scoreDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Score> findSearch(Map whereMap) {
		Specification<Score> specification = createSpecification(whereMap);
		return scoreDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Score findById(String id) {
		return scoreDao.findById(id).orElse(null);
	}

	/**
	 * 增加
	 * @param score
	 */
	public void add(Score score) {
		score.setId( idWorker.nextId()+"" );
		scoreDao.save(score);
	}

	/**
	 * 修改
	 * @param score
	 */
	public void update(Score score) {
		scoreDao.save(score);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		scoreDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Score> createSpecification(Map searchMap) {

		return new Specification<Score>() {

			@Override
			public Predicate toPredicate(Root<Score> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // 序列号
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                	predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 学号
                if (searchMap.get("student_num")!=null && !"".equals(searchMap.get("student_num"))) {
                	predicateList.add(cb.like(root.get("student_num").as(String.class), "%"+(String)searchMap.get("student_num")+"%"));
                }
                // 课程编号
                if (searchMap.get("course_num")!=null && !"".equals(searchMap.get("course_num"))) {
                	predicateList.add(cb.like(root.get("course_num").as(String.class), "%"+(String)searchMap.get("course_num")+"%"));
                }
                // 成绩
                if (searchMap.get("score")!=null && !"".equals(searchMap.get("score"))) {
                	predicateList.add(cb.like(root.get("score").as(String.class), "%"+(String)searchMap.get("score")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
