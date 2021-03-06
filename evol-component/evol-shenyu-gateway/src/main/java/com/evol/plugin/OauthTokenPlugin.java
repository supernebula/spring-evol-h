package com.evol.plugin;

import com.alibaba.fastjson.JSON;
import com.evol.constant.Constants;
import com.evol.domain.LoginUser;
import com.evol.enums.ApiResponseEnum;
import com.evol.plugin.pojo.OauthTokenRuleDTO;
import com.evol.util.RedisClientUtil;
import com.evol.web.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import org.apache.shenyu.common.dto.RuleData;
import org.apache.shenyu.common.dto.SelectorData;
import org.apache.shenyu.common.enums.PluginEnum;
import org.apache.shenyu.plugin.api.ShenyuPluginChain;
import org.apache.shenyu.plugin.base.AbstractShenyuPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

@Component
@Slf4j
public class OauthTokenPlugin extends AbstractShenyuPlugin {

    @Autowired
    private RedisClientUtil redisClientUtil;

    @Override
    public int getOrder() {
        return PluginEnum.SPRING_CLOUD.getCode()-3;
    }

    @Override
    public String named(){
        return "oauthToken";
    }

    @Override
    public boolean skip(final ServerWebExchange exchange){
        return false;
    }

    @Override
    protected Mono<Void> doExecute(ServerWebExchange exchange, ShenyuPluginChain chain, SelectorData selector,
                                   RuleData rule){
        ServerHttpRequest request = Objects.requireNonNull(exchange).getRequest();
        ServerHttpResponse response = Objects.requireNonNull(exchange).getResponse();
        List<String> authHeaders = request.getHeaders().get("Authorization");
        String token = (authHeaders != null && authHeaders.size() != 0) ? authHeaders.get(0) : null;

        String ruleHandle = rule.getHandle();

        final OauthTokenRuleDTO ruleDto = JSON.toJavaObject(JSON.parseObject(ruleHandle), OauthTokenRuleDTO.class);
        String path = request.getPath().toString();

        //??????token?????????url???????????????
        if(ruleDto.getIgnorePathList().contains(path)){
            return chain.execute(exchange);
        }

        //???token??????????????????
        if(StringUtils.isBlank(token)){
            unauthResponse(response, exchange, chain);
        }

        String key = Constants.TOKEN + token;
        LoginUser loginUser = redisClientUtil.getByKey(key);
        if(loginUser == null){
            return unauthResponse(response, exchange, chain);
        }
        return chain.execute(exchange);
    }

    private Mono<Void> unauthResponse(ServerHttpResponse response, ServerWebExchange exchange, ShenyuPluginChain chain){

        ApiResponse apiResponse = ApiResponse.fail(ApiResponseEnum.ACCESS_TOKEN_INVALID, "???????????????token");
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.writeWith(Mono.just(response.bufferFactory().wrap(JSON.toJSONString(apiResponse).getBytes(StandardCharsets.UTF_8))));
    }

}
