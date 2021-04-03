package com.example.hiringtask.network;


import androidx.annotation.IntDef;

/*
 * Created by Hisham Snaimeh on 5/6/2018.
 */
@IntDef({
        HttpStatus.SUCCESS,
        HttpStatus.BAD_REQUEST,
        HttpStatus.FORBIDDEN,
        HttpStatus.NOT_FOUND,
        HttpStatus.TOO_MANY_REQUESTS,
        HttpStatus.SERVER_ERROR,
        HttpStatus.CLIENT_ERROR,
        HttpStatus.JSON_ERROR,
        HttpStatus.NETWORK_ERROR,
        HttpStatus.UNAUTHENTICATED_ERROR,
        HttpStatus.UNEXPECTED_ERROR
})

public @interface HttpStatus {

    int SUCCESS = 0;
    int BAD_REQUEST = 1;
    int FORBIDDEN = 2;
    int NOT_FOUND = 3;
    int TOO_MANY_REQUESTS = 4;
    int SERVER_ERROR = 5;
    int CLIENT_ERROR = 6;
    int JSON_ERROR = 7;
    int NETWORK_ERROR = 8;
    int UNAUTHENTICATED_ERROR = 9;
    int UNEXPECTED_ERROR = 10;
}
