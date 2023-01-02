package com.wheogus.myportfolio.domain;

import org.springframework.web.util.UriComponentsBuilder;

public class SearchCondition {

    private Integer page = 1;
    private Integer pageSize = 10;
    private String keyword = "";
    private String option = "";

    public String getQueryString(Integer page) {
        return UriComponentsBuilder.newInstance()
                .queryParam("page", page)
                .queryParam("pageSize", pageSize)
                .queryParam("option", option)
                .queryParam("keyword", keyword)
                .build().toString();
    }

    public String getQueryString() {
        return getQueryString(page);
    }


    public SearchCondition() {}
    public SearchCondition(Integer page, Integer pageSize) {
        this(page, pageSize, "", "");
    }
    public SearchCondition(Integer page, Integer pageSize, String keyword, String option) {
        this.page = page;
        this.pageSize = pageSize;
        this.keyword = keyword;
        this.option = option;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    @Override
    public String toString() {
        return "SearchCondition{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", offSet=" + getOffset() +
                ", keyword='" + keyword + '\'' +
                ", option='" + option + '\'' +
                '}';
    }

    public Integer getOffset() {
        return (page - 1) * pageSize;
    }
}
