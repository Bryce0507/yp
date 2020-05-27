package com.yp.common.utils;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zxb
 * @date 2020/5/27 17:00
 */
public class EntityConverterUtil {
    public static final <T> T  convert(Object from , Class<T> to){
        String json = JSON.toJSONString(from);
        T toObject = JSON.parseObject(json,to);
        return toObject;
    }
    public static final <T> List<T> convertList(Object fromList , Class<T> to){
        Assert.isInstanceOf(ArrayList.class,fromList);
        List<Object> from = (List<Object>)fromList;
        if(from == null){
            return null;
        }
        List<T> list = new ArrayList<T>();
        if(from.size()>0){
            for(Object o : from){
                T toObject = convert(o,to);
                list.add(toObject);
            }
        }
        return list;
    }
}