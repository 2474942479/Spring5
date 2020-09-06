package edu.zsq.webfluxdemo.service.impl;

import edu.zsq.webfluxdemo.entity.User;
import edu.zsq.webfluxdemo.mapper.UserMapper;
import edu.zsq.webfluxdemo.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zsq
 * @since 2020-09-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     *  零个或一个元素用Mono
     *  根据id查询用户
     * @param id
     * @return
     */
    @Override
    public Mono<User> getUserById(String id) {
        User user = userMapper.selectById(id);
        return Mono.justOrEmpty(user);
    }


    /**
     *  多个元素用Flux
     * 获取所有用户
     * @return
     */
    @Override
    public Flux<User> getAllUser() {
        List<User> users = userMapper.selectList(null);
        return Flux.fromIterable(users);
    }


    /**
     * 添加用户
     * @return
     */
    @Override
    public Mono<Void> insertUser(Mono<User> userMono) {
        User user = new User();
        Mono<Void> voidMono = userMono.doOnNext(userInfo -> {
            BeanUtils.copyProperties(userInfo, user);
        }).thenEmpty(Mono.empty());
        userMapper.insert(user);
        return voidMono;
    }
}
