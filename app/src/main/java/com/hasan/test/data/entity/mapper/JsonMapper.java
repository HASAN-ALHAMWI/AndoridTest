package com.hasan.test.data.entity.mapper;

import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class JsonMapper {

    private Gson gson = new Gson();

    @Inject
    public JsonMapper() {
    }

    synchronized public String map(Object model) {
        if (model == null)
            return null;
        return gson.toJson(model);
    }

    public <T> T unmap(Class<T> type, String jsonString) {
        if (jsonString == null || jsonString.isEmpty())
            return null;
        return gson.fromJson(jsonString, type);
    }
}
