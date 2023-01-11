package com.wheogus.myportfolio.dao;

import com.wheogus.myportfolio.domain.CommentDto;

import java.util.List;

public interface CommentDao {
    int count(Integer num) throws Exception;

    List<CommentDto> selectAll(Integer num) throws Exception;

    CommentDto select(Integer cno) throws Exception;

    int delete(Integer cno, String commenter) throws Exception;

    int update(CommentDto commentDto) throws Exception;

    int insert(CommentDto commentDto) throws Exception;


    int deleteBoardComment(Integer num) throws Exception;
}

