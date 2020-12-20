package com.example.demo.util;

import com.example.demo.entity.UserEntity;
import com.example.demo.mongo.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public final class BeanCopyUtil {


    public static <T, V> T copy(T t, V v) {
        if (v instanceof UserDto && t instanceof UserEntity) {
            ((UserEntity) t).setFirstName(((UserDto) v).getFirstName());
            ((UserEntity) t).setLastName(((UserDto) v).getLastName());
        }
        return t;
    }
}

@FunctionalInterface
interface CopyUtil {
    <T, V> T copy(T t, V v);
}