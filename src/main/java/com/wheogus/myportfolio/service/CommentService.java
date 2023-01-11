package com.wheogus.myportfolio.service;

import com.wheogus.myportfolio.domain.CommentDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentService {
    int count(Integer num) throws Exception;

    List<CommentDto> getList(Integer num) throws Exception;

    CommentDto read(Integer cno) throws Exception;

    @Transactional(rollbackFor = Exception.class)
    int delete(Integer cno, Integer num, String commenter) throws Exception;

    @Transactional(rollbackFor = Exception.class)
    int write(CommentDto commentDto) throws Exception;

    int modify(CommentDto commentDto) throws Exception;

    int deleteBoardComment(Integer num) throws Exception;
}