package com.tech.nate.androidspringauthentication;

import com.tech.nate.androidspringauthentication.model.User;

import retrofit2.http.Body;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {

    @POST
    createPost(@Body User user){

    }

}
