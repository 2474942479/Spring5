package edu.zsq.webfluxdemo.controller;


import edu.zsq.webfluxdemo.entity.User;
import edu.zsq.webfluxdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zsq
 * @since 2020-09-06
 */
@RestController
@RequestMapping("/webflux/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public Mono<User> getUserById(@PathVariable String id){
        return userService.getUserById(id);
    }


    @GetMapping("/getAllUser")
    public Flux<User> getAllUser(){
        return userService.getAllUser();
    }

    @PostMapping("/saveUser")
    public Mono<Void> saveUser(@RequestParam User user){
        Mono<User> userMono = Mono.just(user);
        return userService.insertUser(userMono);
    }
}

