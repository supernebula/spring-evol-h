package com.evol.controller;

import com.evol.constant.Constants;
import com.evol.domain.LoginUser;
import com.evol.domain.dto.LoginParam;
import com.evol.domain.dto.LoginUserDTO;
import com.evol.enums.ApiResponseEnum;
import com.evol.service.StaffService;
import com.evol.util.JwtUtil;
import com.evol.util.RedisClientUtil;
import com.evol.web.ApiResponse;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisClientUtil redisClientUtil;

    @Autowired
    private StaffService staffService;


    //@CrossOrigin(value = "http://localhost:8080")
    @PostMapping("/login")
    public ApiResponse<LoginUserDTO> login(LoginParam param){

        return staffService.login(param);
    }


    //@CrossOrigin(value = "http://localhost:8080")
    @PostMapping("/logout")
    public ApiResponse logout(@RequestParam("token") String token){
        redisClientUtil.deleteByKeys(Constants.TOKEN + token);
        return ApiResponse.success(0);
    }

    //@CrossOrigin(value = "http://localhost:8080")
    @GetMapping("/userInfo")
    public ApiResponse<LoginUser> userInfo(@RequestParam("token") String token){
        Claims claims = jwtUtil.getClaimsByToken(token);
        if(claims == null){
            return ApiResponse.fail(ApiResponseEnum.AUTH_CODE_ERROR);
        }
        String subject = claims.getSubject();
        LoginUser loginUser = redisClientUtil.getObjByKey(Constants.TOKEN + token);
        return ApiResponse.success(loginUser);
    }
}
