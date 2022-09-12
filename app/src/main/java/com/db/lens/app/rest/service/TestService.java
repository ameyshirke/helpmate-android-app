package com.db.lens.app.rest.service;


import com.db.lens.app.model.HttpBin;
import com.db.lens.app.rest.util.RestCall;

import retrofit2.http.GET;

public interface TestService {

    @GET("/ip")
    RestCall<HttpBin> getIp();
}
