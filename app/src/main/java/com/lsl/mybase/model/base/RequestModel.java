package com.lsl.mybase.model.base;

/**
 * author:Created by lsl on 2017/5/2 15:08.
 * description: 请求封装
 */

public class RequestModel<T> {
    private HeadBean head;
    private T body;

    public HeadBean getHead() {
        return head;
    }

    public void setHead(HeadBean head) {
        this.head = head;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
