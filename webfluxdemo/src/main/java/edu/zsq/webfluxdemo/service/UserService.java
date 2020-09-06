package edu.zsq.webfluxdemo.service;

import edu.zsq.webfluxdemo.entity.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author 张
 */
public interface UserService {

    /**
     *  零个或一个元素用Mono
     *  根据id查询用户
     * @param id
     * @return
     */
    Mono<User> getUserById(String id);

    /**
     *  多个元素用Flux
     * 获取所有用户
     * @return
     */
    Flux<User> getAllUser();

    /**
     * 添加用户
     * @param userMono
     * @return
     */
    Mono<Void> insertUser(Mono<User> userMono);


}
