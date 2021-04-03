package com.example.hiringtask.network;


import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import com.example.hiringtask.R;

import java.io.IOException;
import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyCallAdapter<T> implements MyCall<T> {

    private final Call<T> call;
    private final Executor callbackExecutor;
    private Handler handler = new Handler(Looper.getMainLooper());

    public MyCallAdapter(Call<T> call, Executor callbackExecutor) {
        this.call = call;
        this.callbackExecutor = callbackExecutor;
    }

    @Override
    public void cancel() {
        call.cancel();
    }

    @Override
    public void enqueue(final MyCallback<T> callback) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
                int code = response.code();
                HttpResult<T> result = new HttpResult<>();
                result.setCode(code);
                result.setCall(MyCallAdapter.this);
                result.setResult(response.body());
                if (code >= 200 && code < 300)
                    result.setStatus(HttpStatus.SUCCESS);
                else if (code == 400) {
                    result.setStatus(HttpStatus.BAD_REQUEST);
                    result.setLocalizedMsg(R.string.unauthenticated_error);
                } else if (code == 401) {
                    result.setStatus(HttpStatus.UNAUTHENTICATED_ERROR);
                    result.setLocalizedMsg(R.string.unauthenticated_error);
                } else if (code == 403) {
                    result.setStatus(HttpStatus.FORBIDDEN);
                    result.setLocalizedMsg(R.string.unauthenticated_error);
                } else if (code == 404) {
                    result.setStatus(HttpStatus.NOT_FOUND);
                    result.setLocalizedMsg(R.string.unauthenticated_error);
                } else if (code == 429) {
                    result.setStatus(HttpStatus.TOO_MANY_REQUESTS);
                    result.setLocalizedMsg(R.string.to_many_attempts);
                } else if (code >= 500 && code < 600) {
                    result.setStatus(HttpStatus.SERVER_ERROR);
                    result.setLocalizedMsg(R.string.server_error);
                } else {
                    result.setStatus(HttpStatus.UNEXPECTED_ERROR);
                    result.setLocalizedMsg(R.string.unexpected_error);
                }
                handler.post(() -> callback.onResult(result));
            }

            @Override
            public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
                HttpResult<T> result = new HttpResult<>();
                result.setCall(MyCallAdapter.this);
                result.setThrowable(t);
                if (t instanceof IOException) {
                    result.setStatus(HttpStatus.NETWORK_ERROR);
                    result.setLocalizedMsg(R.string.network_error);
                } else {
                    result.setStatus(HttpStatus.UNEXPECTED_ERROR);
                    result.setLocalizedMsg(R.string.unexpected_error);
                }
                handler.post(() -> callback.onResult(result));
            }
        });
    }

    @Override
    public MyCall<T> clone() {
        return new MyCallAdapter<>(call.clone(), callbackExecutor);
    }
}
