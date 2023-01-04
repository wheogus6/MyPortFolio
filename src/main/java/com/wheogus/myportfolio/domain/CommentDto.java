package com.wheogus.myportfolio.domain;

import java.util.Date;
import java.util.Objects;

public class CommentDto {

    private Integer cno;
    private Integer num;
    private String comment;
    private String commenter;
    private Date reg_date;

    public CommentDto() {}

    public CommentDto(Integer cno, Integer num, String comment, String commenter, Date reg_date) {
        this.cno = cno;
        this.num = num;
        this.comment = comment;
        this.commenter = commenter;
        this.reg_date = reg_date;
    }

    public Integer getCno() {
        return cno;
    }

    public void setCno(Integer cno) {
        this.cno = cno;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommenter() {
        return commenter;
    }

    public void setCommenter(String commenter) {
        this.commenter = commenter;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "cno=" + cno +
                ", num=" + num +
                ", comment='" + comment + '\'' +
                ", commenter='" + commenter + '\'' +
                ", reg_date=" + reg_date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentDto that = (CommentDto) o;
        return Objects.equals(cno, that.cno) && Objects.equals(num, that.num) && Objects.equals(comment, that.comment) && Objects.equals(commenter, that.commenter) && Objects.equals(reg_date, that.reg_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cno, num, comment, commenter, reg_date);
    }
}
