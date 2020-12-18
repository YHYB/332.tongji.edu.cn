package com.learn.demo.springbootdemo.service;

import com.learn.demo.springbootdemo.dao.UserDao;
import com.learn.demo.springbootdemo.pojo.User;
import com.learn.demo.springbootdemo.util.IdWorker;
import com.learn.demo.springbootdemo.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author chenbin
 * @description: 服务层
 * @version: V1.0
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BCryptPasswordEncoder encoder;

    /**
     * 查询全部列表
     *
     * @return
     */
    public List<User> findAll() {
        return userDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<User> findSearch(Map whereMap, int page, int size) {
        Specification<User> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return userDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<User> findSearch(Map whereMap) {
        Specification<User> specification = createSpecification(whereMap);
        return userDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param id
     * @return
     */
    public User findById(String id) {
        return userDao.findById(id).orElse(null);
    }

    /**
     * 增加
     *
     * @param user
     */
    public boolean add(User user) {
        Map<String,String> map = new HashMap<>();
        map.put("username",user.getUsername());
        List<User> search = findSearch(map);
        if (!CollectionUtils.isEmpty(search)) {
            return false;
        }
        user.setId(idWorker.nextId() + "");
        user.setPassword(encoder.encode(user.getPassword()));
        userDao.save(user);
        return true;
    }

    /**
     * 修改
     *
     * @param user
     */
    public void update(User user) {
        userDao.save(user);
    }

    /**
     * 删除
     *
     * @param id
     */
    public void deleteById(String id) {
        userDao.deleteById(id);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<User> createSpecification(Map searchMap) {

        return new Specification<User>() {

            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 用户名
                if (searchMap.get("username") != null && !"".equals(searchMap.get("username"))) {
                    predicateList.add(cb.equal(root.get("username").as(String.class),  searchMap.get("username")));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    public User login(String username, String password) {
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        List<User> all = this.findSearch(map);
        if (!CollectionUtils.isEmpty(all) && encoder.matches(password, all.get(0).getPassword())) {
            return all.get(0);
        }
        return null;
    }

    /**
     * 修改密码
     *
     * @param user
     */
    public void updatePassword(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userDao.save(user);
    }

    /**
     * 获取用户信息
     *
     * @param token
     * @return
     */
    public Map<String, Object> getInfo(String token) {
        Claims claims = jwtUtil.parseJWT(token);
        String id = claims.getId();
        User one = this.findById(id);
        List<String> list = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        if (one != null) {
            map1.put("name", one.getUsername());
            map1.put("uid", one.getId());
            map1.put("roles", list);
            return map1;
        }
        return null;
    }

    /**
     * 注册
     *
     * @param user
     */
    public boolean register(User user) {
        Map<String, String> map = new HashMap<>();
        map.put("username", user.getUsername());
        List<User> all = this.findSearch(map);
        if (!CollectionUtils.isEmpty(all)) {
            return false;
        }
        user.setPassword(encoder.encode(user.getPassword()));
        user.setId(idWorker.nextId() + "");
        userDao.save(user);
        return true;
    }


}
