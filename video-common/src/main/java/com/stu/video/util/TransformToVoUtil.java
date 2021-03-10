package com.stu.video.util;

import org.springframework.stereotype.Repository;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: kimijiaqili
 * @CreateDate: 2021/3/9 13:31
 * @Version: 1.0
 * @Description:
 */
@Repository
public class TransformToVoUtil<T, V> {

    public List<V> transformToVo(List<T> entityList) {
        List<V> voList = new ArrayList<>();
        for (T entity:entityList) {
            try {
                Method transformMethod = entity.getClass().getMethod("transformToVo");
                V vo = (V)transformMethod.invoke(entity);
                voList.add(vo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return voList;
    }
}
