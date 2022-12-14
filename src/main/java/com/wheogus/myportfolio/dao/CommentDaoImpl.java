package com.wheogus.myportfolio.dao;


import com.wheogus.myportfolio.domain.CommentDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDaoImpl implements CommentDao{

    @Autowired
    private SqlSession session;

    private static String namespace = "com.wheogus.myportfolio.dao.CommentMapper.";

    @Override
    public int count(Integer num) throws Exception{
        return session.selectOne(namespace + "count", num);
    }

    @Override
    public List<CommentDto> selectAll(Integer num)throws Exception{
        return session.selectList(namespace + "selectAll", num);
    }

    @Override
    public CommentDto select(CommentDto commentDto) throws Exception{
        return session.selectOne(namespace + "select", commentDto);
    }

    @Override
    public int delete(Integer cno) throws Exception {
        return session.delete(namespace + "delete", cno);
    }

    @Override
    public int update(CommentDto commentDto) throws Exception {
        return session.update(namespace + "update", commentDto);
    }

    @Override
    public int insert(CommentDto commentDto) throws Exception {
        return session.insert(namespace + "insert", commentDto);
    }

    @Override
    public int deleteBoardComment(Integer num) throws Exception{
        return session.delete(namespace + "deleteBoardComment", num);
    }
}