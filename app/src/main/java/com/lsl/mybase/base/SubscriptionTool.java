package com.lsl.mybase.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * author:Created by lsl on 2017/5/19 17:14.
 * description:
 */

public class SubscriptionTool {

    protected CompositeSubscription mCompositeSubscription;

    protected void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

    /**
     * 统一注册观察者方法，方便管理，避免内存泄漏
     * 所有观察者必须调用，否则请自己回收观察者
     * @param subscription
     */
    public void addSubscrebe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }
}
