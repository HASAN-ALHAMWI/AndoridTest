package com.hasan.test.data.remote.util;

import com.hasan.test.data.entity.mapper.JsonMapper;
import com.hasan.test.data.remote.api.model.base.BaseResponse;


public class RemoteUtil {

    private static final JsonMapper jsonMapper = new JsonMapper();

    public static String getErrorMessage(String jsonError) {
        try {
            if (jsonError != null) {
                BaseResponse baseResponse = jsonMapper.unmap(BaseResponse.class, jsonError);
                if (baseResponse != null) {
                    String status = baseResponse.getStatus();
                    if (status != null)
                        return status;
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}
