package com.van.SmartHome.model;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor

public class BasicResult<T> {
    private T result;

    public BasicResult(T result) {
        this.result = result;
    }

    public static BasicResult<String> success() {
        return new BasicResult<>("success");
    }

    public static BasicResult<String> ok() {
        return new BasicResult<>("ok");
    }

    public static BasicResult<String> failed() {
        return failed("");
    }

    public static BasicResult<String> failed(String error) {
        BasicResult<String> result = new BasicResult<>();
        //result.setError(error);
        return result;
    }

    public static <T> BasicResult<T> of(T object) {
        return new BasicResult<>(object);
    }
}