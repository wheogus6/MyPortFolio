package com.wheogus.myportfolio.dao;

import com.wheogus.myportfolio.domain.BoardDto;
import com.wheogus.myportfolio.domain.SearchCondition;

import java.util.List;
import java.util.Map;

public interface BoardDao {

    int deleteAll() throws Exception;

    int delete(Integer num, String writer) throws Exception;

    int count() throws Exception;

    List<BoardDto> selectAll() throws Exception;

    int insert(BoardDto dto) throws Exception;

    int update(BoardDto dto) throws Exception;

    List<BoardDto> searchSelectPage(SearchCondition sc) throws  Exception;

    int searchResultCnt(SearchCondition sc) throws Exception;

    BoardDto select(Integer num) throws Exception;

    List<BoardDto> selectPage(Map map) throws Exception;

    int increaseViewCnt(Integer num) throws Exception;

    int updateCommentCnt(Integer num, int cnt);
}
