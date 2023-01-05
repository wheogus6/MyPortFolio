package com.wheogus.myportfolio.service;

import com.wheogus.myportfolio.dao.BoardDao;
import com.wheogus.myportfolio.dao.CommentDao;
import com.wheogus.myportfolio.domain.CommentDto;

import java.util.List;

public class CommentService {

    BoardDao boardDao;
    CommentDao commentDao;

    public int count(Integer num) throws Exception{
        return commentDao.count(num);
    }

    public List<CommentDto> selectAll(Integer num) throws Exception{
        return commentDao.selectAll(num);
    }

    public CommentDto select(Integer cno) throws Exception {
        return commentDao.select(cno);
    }

//    public int delete(Integer cno, String commenter) throws Exception {
//
//    }
}
