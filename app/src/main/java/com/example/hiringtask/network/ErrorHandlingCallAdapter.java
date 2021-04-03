package com.example.hiringtask.network;


import androidx.annotation.NonNull;

import java.lang.reflect.Type;
import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.CallAdapter;


public class ErrorHandlingCallAdapter<R> implements CallAdapter<R,MyCall<R>> {


    private final Type type;
    private final Executor callbackExecutor;

    ErrorHandlingCallAdapter(Type type, Executor callbackExecutor) {
        this.type = type;
        this.callbackExecutor = callbackExecutor;
    }

    @Override
    public Type responseType() {
        return type;
    }

    @Override
    public MyCall<R> adapt(@NonNull Call<R> call) {
        return new  MyCallAdapter<>(call, callbackExecutor);
    }
}
