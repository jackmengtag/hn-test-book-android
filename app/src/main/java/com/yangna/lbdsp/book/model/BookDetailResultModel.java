package com.yangna.lbdsp.book.model;

import com.yangna.lbdsp.book.bean.HnBook;
import com.yangna.lbdsp.common.base.BaseModel;

public class BookDetailResultModel extends BaseModel {

    private HnBook body;

    public HnBook getBody() {
        return body;
    }

    public void setBody(HnBook body) {
        this.body = body;
    }
}
