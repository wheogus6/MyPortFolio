package com.wheogus.myportfolio.dao;

import com.wheogus.myportfolio.domain.UserDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SqlSession session;

    private static String namespace = "com.wheogus.myportfolio.dao.UserMapper.";

    @Override
    public int deleteAllUser(){
        return session.delete(namespace + "deleteAll");
    }
    @Override
    public int deleteUser(Integer id){
        Map map = new HashMap();
        map.put("id", id);
        return session.delete(namespace + "delete", map);
    }
    @Override
    public int countUser(){
        return session.selectOne(namespace + "count");
    }
    @Override
    public List<UserDto> selectAllUser(){
        return session.selectOne(namespace + "selectAll");
    }
    @Override
    public int insertUser(UserDto userDto){
        return session.insert(namespace + "insert", userDto);
    }
    @Override
    public int updateUser(UserDto userDto){
        return session.update(namespace + "update", userDto);
    }
}
