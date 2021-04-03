package com.example.hiringtask.network;



public interface MyCallback<T> {

    void onResult( HttpResult<T> result);

}
