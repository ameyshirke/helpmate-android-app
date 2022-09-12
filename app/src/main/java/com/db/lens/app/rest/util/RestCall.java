package com.db.lens.app.rest.util;

import java.io.IOException;
import retrofit2.Response;

public interface RestCall<T> {

    void enqueue(RestCallback<T> callback);

    Response<T> execute() throws IOException;

    RestCall<T> clone();

    void cancel();

}
