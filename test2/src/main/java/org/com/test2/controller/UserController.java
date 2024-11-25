package org.com.test2.controller;

import org.com.test2.entity.UserInfo;
import org.com.test2.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello, Guest!");
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody UserInfo userInfo) {
        String result = userService.addUser(userInfo);
        return ResponseEntity.ok(result);
    }

    @GetMapping("")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Welcome to the home page!");
    }

    @GetMapping("/a")
    public ResponseEntity<String> a() {
        return ResponseEntity.ok("Welcome to the 'a' page!");
    }
}
