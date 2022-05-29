package com.ic1101.middle.aop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cwd
 * @date 5/24/22 10:52 PM
 */
@RestController
public class UserController {
    @GetMapping("/first")
    public Object first( String user) {
        System.out.println("哈哈哈");
        return "first controller";
    }
    @RequestMapping("/doError")
    public Object error() {
        return 1 / 0;
    }


}
