package com.example.hiringtask.network;



public interface MyCall<T> {

    void cancel();

    void enqueue(MyCallback<T> callback);

    MyCall<T> clone();
}
