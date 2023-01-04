package com.wheogus.myportfolio.service;

import com.wheogus.myportfolio.dao.BoardDao;
import com.wheogus.myportfolio.domain.BoardDto;
import com.wheogus.myportfolio.domain.SearchCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
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

    @Override
    public List<BoardDto> getSearchSelectPage(SearchCondition sc) throws Exception{
        return boardDao.searchSelectPage(sc);
    }

    @Override
    public int getSearchResultCnt(SearchCondition sc) throws Exception {
        return boardDao.searchResultCnt(sc);
    }

    @Override
    public BoardDto read(Integer num) throws Exception {
        BoardDto boardDto = boardDao.select(num);
        boardDao.increaseViewCnt(num);     //데이터 베이스에서 view_cnt 디폴트 값을 0으로 설정 해야 조회수 오름
        return boardDto;
    }

    @Override
    public List<BoardDto> selectPage(Map map) throws Exception {
        return boardDao.selectPage(map);
    }




}
