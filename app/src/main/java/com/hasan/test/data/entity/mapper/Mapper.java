package com.hasan.test.data.entity.mapper;

import java.util.ArrayList;
import java.util.List;

public abstract class Mapper<TO, FROM> {

    public abstract TO map(FROM model);

    public FROM unmap(TO model) {
        return null;
    }

    public List<TO> map(List<FROM> models) {
        List<TO> list = new ArrayList<>();
        for (FROM model :
                models) {
            list.add(map(model));
        }
        return list;
    }

    public List<FROM> unmap(List<TO> models) {
        List<FROM> list = new ArrayList<>();
        for (TO model :
                models) {
            list.add(unmap(model));
        }
        return list;
    }

}
