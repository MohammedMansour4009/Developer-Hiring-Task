package com.example.hiringtask.network;

import androidx.annotation.StringRes;



public class HttpResult<T> {

    private int code;
    private MyCall<T> call;
    private T result;
    private int status;
    private Throwable throwable;
    private int localizedMsg;
    private int errorIcon;
    private boolean hasRetry;
    private int retryMsg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public MyCall<T> getCall() {
        return call;
    }

    public void setCall(MyCall<T> call) {
        this.call = call;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }



    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setLocalizedMsg(@StringRes int localizedMsg) {
        this.localizedMsg = localizedMsg;
    }

    @StringRes
    public int getLocalizedMsg() {
        return localizedMsg;
    }
}
