package com.van.SmartHome.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.time.Instant;
import java.util.Date;

import static com.van.SmartHome.util.DateUtil.compatibilityDate;
import static org.apache.commons.lang3.math.NumberUtils.isCreatable;

@ReadingConverter
public class StringToDateConverter implements Converter<String, Date> {
	@Override
	public Date convert(String source) {
        if (isCreatable(source)) {
            return compatibilityDate(Long.valueOf(source));
        } else {
            return Date.from(Instant.parse(source));
        }
	}
}
