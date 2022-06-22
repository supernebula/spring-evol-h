package com.evol.plugin;

import com.alibaba.fastjson.JSON;
import com.evol.constant.Constants;
import com.evol.domain.LoginUser;
import com.evol.enums.ApiResponseEnum;
import com.evol.util.RedisClientUtil;
import com.evol.web.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

/**
 * @author admin
 */
@Component
@Slf4j
public class OauthTokenPluginFilter implements GlobalFilter, Ordered {

    private final String ignorePath = "/api-auth/login;/api-auth/logout";

    private final boolean skip = false;

    @Autowired
    private RedisClientUtil redisClientUtil;

    @Override
    public int getOrder() {
        return -1;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        if(skip){
            return chain.filter(exchange);
        }

        ServerHttpRequest request = Objects.requireNonNull(exchange).getRequest();
        ServerHttpResponse response = Objects.requireNonNull(exchange).getResponse();
        List<String> authHeaders = request.getHeaders().get("Authorization");
        String token = (authHeaders != null && authHeaders.size() != 0) ? authHeaders.get(0) : null;
        if("undefined".equals(token)){
            token = null;
        }
        String path = request.getPath().toString();

        //忽略token验证的url，直接放行
        if(ignorePath.contains(path)){
            return chain.filter(exchange);
        }

        //无token，返回无授权
        if(StringUtils.isBlank(token)){
            unauthResponse(response, exchange, chain);
        }

        String key = Constants.TOKEN + token;
        LoginUser loginUser = redisClientUtil.getObjByKey(key);
        if(loginUser == null){
            return unauthResponse(response, exchange, chain);
        }
        return chain.filter(exchange);
    }

    private Mono<Void> unauthResponse(ServerHttpResponse response, ServerWebExchange exchange, GatewayFilterChain chain){
        ApiResponse apiResponse = ApiResponse.fail(ApiResponseEnum.ACCESS_TOKEN_INVALID, "无效的访问token");
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.writeWith(Mono.just(response.bufferFactory().wrap(JSON.toJSONString(apiResponse).getBytes(StandardCharsets.UTF_8))));
    }
}
