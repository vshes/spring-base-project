package com.example.demo.util;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class MongoDateTimeReadConverter implements Converter<Date, ZonedDateTime> {
    @Override
    public ZonedDateTime convert(Date date) {
        return date.toInstant().atZone(ZoneOffset.UTC);
    }
}
