package com.wheogus.myportfolio.domain;

public class PageHandler {

    private SearchCondition sc;

    private int naviSize = 10;

    private int totalPage;

    private int totalCnt;

    private int beginP;

    private int endP;

    private boolean showPrev;

    private boolean showNext;

    public PageHandler(int totalCnt, SearchCondition sc) {
        this.totalCnt = totalCnt;
        this.sc =  sc;

        doPaging(totalCnt, sc);
    }

    private void doPaging(int totalCnt, SearchCondition sc) {
        this.totalCnt = totalCnt;
            // Math.ceil : 소수값이 존재할 때 값을 올림
        totalPage = (int) Math.ceil(totalCnt / (double) sc.getPageSize());
        beginP = (sc.getPage()-1)/naviSize * naviSize +1;
        endP = Math.min(beginP + naviSize -1, totalPage);
        showPrev = beginP != 1;
        showNext = endP != totalPage;
    }

    public SearchCondition getSc() {
        return sc;
    }

    public void setSc(SearchCondition sc) {
        this.sc = sc;
    }

    public int getNaviSize() {
        return naviSize;
    }

    public void setNaviSize(int naviSize) {
        this.naviSize = naviSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(int totalCnt) {
        this.totalCnt = totalCnt;
    }

    public int getBeginP() {
        return beginP;
    }

    public void setBeginP(int beginP) {
        this.beginP = beginP;
    }

    public int getEndP() {
        return endP;
    }

    public void setEndP(int endP) {
        this.endP = endP;
    }

    public boolean isShowPrev() {
        return showPrev;
    }

    public void setShowPrev(boolean showPrev) {
        this.showPrev = showPrev;
    }

    public boolean isShowNext() {
        return showNext;
    }

    public void setShowNext(boolean showNext) {
        this.showNext = showNext;
    }

    @Override
    public String toString() {
        return "PageHandler{" +
                "sc=" + sc +
                ", naviSize=" + naviSize +
                ", totalPage=" + totalPage +
                ", totalCnt=" + totalCnt +
                ", beginP=" + beginP +
                ", endP=" + endP +
                ", showPrev=" + showPrev +
                ", showNext=" + showNext +
                '}';
    }
}
