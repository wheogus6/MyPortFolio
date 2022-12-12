package com.wheogus.myportfolio.dao;

import com.wheogus.myportfolio.domain.BoardDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDaoImpl implements BoardDao {

    @Autowired
    private SqlSession session;

    private static String namespace = "com.wheogus.myportfolio.dao.BoardMapper.";


    @Override
    public int deleteAll() throws Exception {
        return session.delete(namespace + "deleteAll");
    }

    @Override
    public int delete(Integer num, String writer) throws Exception {
        return session.delete(namespace + "delete");
    }

    @Override
    public int count() throws Exception {
        return session.selectOne(namespace + "count");
    }

    @Override
    public List<BoardDto> selectAll() throws Exception {
        return session.selectList(namespace + "selectAll");
    }

    @Override
    public int insert(BoardDto dto) throws Exception {
        return session.insert(namespace + "insert");
    }

    @Override
    public int update(BoardDto dto) throws Exception {
        return session.update(namespace + "update");
    }
}
