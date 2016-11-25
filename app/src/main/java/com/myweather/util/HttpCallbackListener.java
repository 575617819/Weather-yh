package com.myweather.util;

/**
 * Created by Administrator on 2016/11/23.
 */
public interface HttpCallbackListener {

    void onFinish(String response);
    void onError(Exception e);
}
