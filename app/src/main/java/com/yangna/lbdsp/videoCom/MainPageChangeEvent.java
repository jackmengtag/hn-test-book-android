package com.yangna.lbdsp.videoCom;

/**
 * create by libo
 * create on 2020-05-21
 * description
 */
public class MainPageChangeEvent {
    private int page;

    public MainPageChangeEvent(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
