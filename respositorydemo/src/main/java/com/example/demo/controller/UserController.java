package com.example.demo.controller;

import com.example.demo.mongo.dto.UserDto;
import com.example.demo.service.UserService;
import com.example.demo.util.Response;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class UserController {
    @Autowired
    private UserService userService;


    @RequestMapping(path = "/user",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    public ResponseEntity<Response<UserDto>> createUser(@RequestBody UserDto userDto) {
        Response res = new Response();
        try {
            Optional<UserDto> response = Optional.ofNullable(userService.createUser(userDto));
            if (response.isPresent()) {
                return res.success("created User", "200", response.get());
            }
            throw new Exception("Invalid Exception");
        } catch (Exception e) {
            return res.failure("Failed", "409", "Try new Data");
        }

    }

    @RequestMapping(path = "/user",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.PUT
    )
    public ResponseEntity<Response<UserDto>> updateUser(@RequestBody UserDto userDto) {
        Response res = new Response();
        try {
            Optional<UserDto> response = Optional.ofNullable(userService.updateUser(userDto));
            if (response.isPresent()) {
                return res.success("UPDATED USER", "200", response.get());
            }
            throw new Exception("Invalid Exception");
        } catch (Exception e) {
            return res.failure("Failed", "409", "Try new Data");
        }
    }

    @RequestMapping(path = "/user/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<Response<UserDto>> getUserById(@PathVariable("id") String id) {
        Response res = new Response();
        try {
            Optional<UserDto> response = Optional.ofNullable(userService.getUserById(id));
            if (response.isPresent()) {
                return res.success("created User", "200", response.get());
            }
            throw new Exception("Invalid Exception");
        } catch (Exception e) {
            return res.failure("Failed", "409", "Invalid Id");
        }
    }

    @RequestMapping(path = "/users",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<Response<List<UserDto>>> getUsers() {
        Response res = new Response();
        try {
            Optional<List<UserDto>> response = Optional.ofNullable(userService.getUsers());
            if (response.isPresent()) {
                return res.success("created User", "200", response.get());
            }
            throw new Exception("Invalid Exception");
        } catch (Exception e) {
            return res.failure("Failed", "409", "No users found");
        }
    }

    @RequestMapping(path = "/user/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.DELETE
    )
    public ResponseEntity<Response<UserDto>> deleteUserById(@PathVariable("id") String id) {
        Response res = new Response();
        try {
            Optional<UserDto> response = Optional.ofNullable(userService.deleteUserById(id));
            if (response.isPresent()) {
                return res.success(" User deleted", "200",  response.get());
            }
            throw new Exception("Invalid Exception");
        } catch (Exception e) {
            return res.failure("Failed", "409", "No users found");
        }
    }
}
