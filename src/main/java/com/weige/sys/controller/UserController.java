package com.weige.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.weige.common.vo.Result;
import com.weige.sys.entity.User;
import com.weige.sys.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.parser.Token;
import org.apache.catalina.mbeans.ClassNameMBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.*;

import javax.security.auth.callback.PasswordCallback;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author laocai
 * @since 2023-07-17
 */
@Api(tags = {"用户接口列表"})
@RestController
@RequestMapping("/user")
//@CrossOrigin
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/all")
//    @CrossOrigin
    public Result<List<User>> getAllUser(){
        List<User> list = userService.list();
        return Result.success(list,"查询成功");
    }

    @ApiOperation("用户登入接口")
    @PostMapping("/login")
//    @CrossOrigin
    public Result<Map<String,Object>> login(@RequestBody User user) {
        Map<String, Object> data = userService.login(user);
        if (data != null) {
            return Result.success(data);
        }
        return Result.fail(20002, "用户名或密码错误");
    }

    @GetMapping("/info")
//    @CrossOrigin
    public Result<Map<String,Object>> getUserInfo(@RequestParam("token") String token){
        //根据token获取用户信息,redis
        Map<String, Object> data = userService.getUserInfo(token);
        if (data != null) {
            return Result.success(data);
        }
        return Result.fail(20003, "登入信息无效，请重新登入");
    }

    @PostMapping("/logout")
//    @CrossOrigin
    public Result<?> logout(@RequestHeader("X-Token") String token ){
        userService.logout(token);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<Map<String,Object>> getUserList(@RequestParam(value = "username",required = false) String username,
                                                  @RequestParam(value = "phone",required = false) String phone,
                                                  @RequestParam(value = "pageNo") Long pageNo,
                                                  @RequestParam(value = "pageSize")Long pageSize){

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(username),User::getUsername,username);
        wrapper.eq(StringUtils.hasLength(phone),User::getPhone,phone);
        wrapper.orderByDesc(User::getId);


        Page<User> page = new Page<>(pageNo,pageSize);
        userService.page(page,wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());

        return Result.success(data);

    }



    @PostMapping
    public Result<?> addUser(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addUser(user);
        return Result.success("用户添加成功");
    }

    @PutMapping
    public Result<?> updateUser(@RequestBody User user){
        user.setPassword(null);
        userService.updateUser(user);
        return Result.success("修改用户成功");
    }

    @GetMapping("/{id}")
    public Result<User> getUserById(@PathVariable("id") Integer id ){
        User user = userService.getUserById(id);
        return Result.success(user);
    }

    @DeleteMapping("/{id}")
    public Result<User> deleteUserById(@PathVariable("id") Integer id ) {
        userService.deleteUserById(id);
        return Result.success("删除成功");
    }


}
