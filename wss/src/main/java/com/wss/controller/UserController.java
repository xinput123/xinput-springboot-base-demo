package com.wss.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @date 2020-09-07 11:12
 */
@RestController
public class UserController {

    @GetMapping("/test")
    public String test() {
        return "。。。。";
    }
}
