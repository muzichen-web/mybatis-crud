package com.du.dao;

import com.du.pojo.User;
import com.du.vo.QueryVo;

import java.util.List;

public interface UserDao {

    //查询用户
    List<User> findAll();

    //查询单个用户
    User findByIdUser(int id);

    //根据名称模糊查询用户信息
    List<User> findByName(String username);

    //根据queryVo中的条件查询用户
    List<User> findUserByVo(QueryVo vo);

    //根据传入参数条件查询
    //查询的条件：有可能有用户名，有可能有地址，有可能都有，有可能都没有
    List<User> findUserByCondition(User user);

    //根据queryvo中提供的id集合，查询用户信息
    List<User> findUserInIds(QueryVo vo);

}
