package com.van.SmartHome.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Slf4j
@RestController
@RequestMapping("api")
public class APITest {

    /**
     * 测试
     */

    @GetMapping(value = "/test")
    public
    @ResponseBody
    Object apiTest(
            @RequestParam(value = "content",required = true) String content

    ) {
        log.info("!!!content:"+content);


        return content;
    }


}
