package com.wheogus.myportfolio.service;

import com.wheogus.myportfolio.dao.BoardDao;
import com.wheogus.myportfolio.domain.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BoardServiceImpl implements BoardService {

   @Autowired
    BoardDao boardDao;

    @Override
    public int removeAll() throws Exception{
        return boardDao.deleteAll();
    }

    @Override
    public int remove(Integer num, String writer) throws Exception {
        return boardDao.delete(num, writer);
    }

    @Override
    public int getCount() throws Exception{
        return boardDao.count();
    }

    @Override
    public int insert(BoardDto dto) throws Exception{
        return boardDao.insert(dto);
    }

    @Override
    public List<BoardDto> getList() throws Exception {
        return boardDao.selectAll();
    }

    @Override
    public int modify(BoardDto dto) throws Exception {
        return boardDao.update(dto);
    }






}