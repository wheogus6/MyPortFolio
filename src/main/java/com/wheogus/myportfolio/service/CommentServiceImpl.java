package com.wheogus.myportfolio.service;

import com.wheogus.myportfolio.dao.BoardDao;
import com.wheogus.myportfolio.dao.CommentDao;
import com.wheogus.myportfolio.domain.CommentDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    BoardDao boardDao;
    CommentDao commentDao;

    public CommentServiceImpl(CommentDao commentDao, BoardDao boardDao){
        this.commentDao = commentDao;
        this.boardDao = boardDao;
    }
    @Override
    public int count(Integer num) throws Exception{
        return commentDao.count(num);
    }
    @Override
    public List<CommentDto> getList(Integer num) throws Exception{
        return commentDao.selectAll(num);
    }
    @Override
    public CommentDto read(CommentDto commentDto) throws Exception {
        return commentDao.select(commentDto);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(Integer cno, Integer num) throws Exception {
        boardDao.updateCommentCnt(num, -1);
        return commentDao.delete(cno);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int write(CommentDto commentDto) throws Exception {
        boardDao.updateCommentCnt(commentDto.getNum(), 1);  //Board 와 comment의 num은 같은 숫자
        return commentDao.insert(commentDto);
    }
    @Override
    public int modify(CommentDto commentDto) throws Exception {
        return commentDao.update(commentDto);
    }

    @Override
    public int deleteBoardComment(Integer num) throws Exception{
        return commentDao.deleteBoardComment(num);
    }
}