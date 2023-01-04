package com.wheogus.myportfolio.dao;


import com.wheogus.myportfolio.domain.CommentDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public CommentDto select(Integer cno) throws Exception{
        return session.selectOne(namespace + "select", cno);
    }

    @Override
    public int delete(Integer cno, String commenter) throws Exception {
        Map map = new HashMap();
        map.put("cno", cno);
        map.put("commenter", commenter);
        return session.delete(namespace + "delete", map);
    }

    @Override
    public int update(CommentDto commentDto) throws Exception {
        return session.update(namespace + "update", commentDto);
    }

}
