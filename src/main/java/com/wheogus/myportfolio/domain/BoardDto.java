package com.wheogus.myportfolio.domain;

import java.util.Date;
import java.util.Objects;

public class BoardDto {

    private Integer num;
    private String title;
    private String writer;
    private String content;
    private int comment_cnt;
    private int view_cnt;
    private Date reg_date;

    public BoardDto() {
        this("", "", "");}
    public BoardDto(String title, String writer, String content) {
        this.title = title;
        this.writer = writer;
        this.content = content;
    }

    public Integer getNum() {
        return num;
    }

    public Integer setNum(Integer num) {
        this.num = num;
        return 0;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getComment_cnt() {
        return comment_cnt;
    }

    public void setComment_cnt(int comment_cnt) {
        this.comment_cnt = comment_cnt;
    }

    public int getView_cnt() {
        return view_cnt;
    }

    public void setView_cnt(int view_cnt) {
        this.view_cnt = view_cnt;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    @Override
    public String toString() {
        return "BoardDto{" +
                "num=" + num +
                ", title='" + title + '\'' +
                ", writer='" + writer + '\'' +
                ", content='" + content + '\'' +
                ", comment_cnt=" + comment_cnt +
                ", view_cnt=" + view_cnt +
                ", reg_date=" + reg_date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardDto boardDto = (BoardDto) o;
        return Objects.equals(num, boardDto.num) && Objects.equals(title, boardDto.title) && Objects.equals(writer, boardDto.writer) && Objects.equals(content, boardDto.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, title, writer, content);
    }
}
