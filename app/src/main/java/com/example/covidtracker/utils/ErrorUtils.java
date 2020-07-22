package com.example.covidtracker.utils;


import com.example.covidtracker.model.APIError;
import com.example.covidtracker.network.ServiceBuilder;

import java.io.IOException;
import java.lang.annotation.Annotation;

import retrofit2.Converter;
import retrofit2.Response;

public class ErrorUtils {

    public static APIError parseError(Response<?> response) {
        Converter<okhttp3.ResponseBody, APIError> converter =
                ServiceBuilder.INSTANCE.getRetrofit()
                        .responseBodyConverter(APIError.class, new Annotation[0]);

        APIError error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new APIError();
        }

        return error;
    }
}