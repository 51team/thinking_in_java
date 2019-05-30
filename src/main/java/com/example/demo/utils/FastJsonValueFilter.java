package com.example.demo.utils;


import com.alibaba.fastjson.serializer.ValueFilter;

public class FastJsonValueFilter  implements ValueFilter {


    @Override
    public Object process(Object object, String name, Object value) {
        return null;
    }
}
