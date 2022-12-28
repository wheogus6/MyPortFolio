package com.wheogus.myportfolio.service;

import com.wheogus.myportfolio.domain.BoardDto;
import com.wheogus.myportfolio.domain.SearchCondition;

import java.util.List;
import java.util.Map;

public interface BoardService {
    int removeAll() throws Exception;

    int remove(Integer num, String writer) throws Exception;

    int getCount() throws Exception;

    int insert(BoardDto dto) throws Exception;


    List<BoardDto> getList() throws Exception;

    int modify(BoardDto dto) throws Exception;

    List<BoardDto> getSearchSelectPage(SearchCondition sc) throws Exception;

    int getSearchResultCnt(SearchCondition sc) throws Exception;

    BoardDto read(Integer num) throws Exception;

    List<BoardDto> selectPage(Map map) throws Exception;
}
