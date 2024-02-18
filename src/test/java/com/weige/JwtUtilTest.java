package com.weige;


import com.weige.common.utils.JwtUtil;
import com.weige.sys.entity.User;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtUtilTest {

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void testCreateJwt() {
        User user = new User();
        user.setUsername("zhangsan");
        user.setPhone("1234567891");
        String token = jwtUtil.createToken(user);
        System.out.println(token);
    }

    @Test
    public void testParseJwt() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhZjNkZTNjOS1iZDdkLTRjZGMtYTJkOC1hYWFkODVhYWU2N2QiLCJzdWIiOiJ7XCJwaG9uZVwiOlwiMTIzNDU2Nzg5MVwiLFwidXNlcm5hbWVcIjpcInpoYW5nc2FuXCJ9IiwiaXNzIjoic3lzdGVtIiwiaWF0IjoxNjkwMzM4NjUwLCJleHAiOjE2OTAzNDA0NTB9.BOglD9T9LTJaFOPZlY4ivH0BuMvRv0wfX3g43zj8sTA";
        Claims claims = jwtUtil.parseToken(token);
        System.out.println(claims);

    }

    @Test
    public void testParseJwt2() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhZjNkZTNjOS1iZDdkLTRjZGMtYTJkOC1hYWFkODVhYWU2N2QiLCJzdWIiOiJ7XCJwaG9uZVwiOlwiMTIzNDU2Nzg5MVwiLFwidXNlcm5hbWVcIjpcInpoYW5nc2FuXCJ9IiwiaXNzIjoic3lzdGVtIiwiaWF0IjoxNjkwMzM4NjUwLCJleHAiOjE2OTAzNDA0NTB9.BOglD9T9LTJaFOPZlY4ivH0BuMvRv0wfX3g43zj8sTA";
        User user = jwtUtil.parseToken(token, User.class);
        System.out.println(user);


    }

}
