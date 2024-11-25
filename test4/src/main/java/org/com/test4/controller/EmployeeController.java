package org.com.test4.controller;


import org.com.test4.config.JwtService;
import org.com.test4.entity.Employee;
import org.com.test4.entity.User;
import org.com.test4.service.EmployeeService;
import org.com.test4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeService service;
    @Autowired
    UserService userService;
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("username: " + userId);

        return service.findAll();
    }

    @PostMapping("/employee")
    public Employee createEmployee(@RequestBody Employee employee) {
        return service.save(employee);
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        userService.addUser(user);

//        return jwtService.generateToken(user.getUserName());
//        generateJwtResponse
        return new ResponseEntity<>(jwtService.generateJwtResponse(user.getUserName()), HttpStatus.OK);

    }
    @PostMapping("/login")
    public String login(@RequestBody User user) {

        try {
            // Attempt authentication
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword())
            );

            // If authentication is successful, generate and return JWT
            if (authenticate.isAuthenticated()) {
                String token = jwtService.generateToken(user.getUserName());
                return token;
            }
            return "Invalid login";
        } catch (Exception ex) {
            // Handle invalid login here
//            return ex.getMessage();
            throw new RuntimeException("Invalid login");
        }
//        // Default response for other cases, if needed
//        return "Invalid login";

    }
}
