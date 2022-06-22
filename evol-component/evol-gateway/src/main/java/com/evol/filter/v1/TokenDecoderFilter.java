package com.evol.filter.v1;

import groovy.util.logging.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;

/**
 * token解析工具类
 */
//@Component
@Order(-1)
@Slf4j
@Deprecated
public class TokenDecoderFilter extends AbstractGatewayFilterFactory {


    //note
    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            try {
                Thread.sleep(70);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }

            //将现在的request 变成 exchange对象
            return chain.filter(exchange.mutate().request(request).build());
        };
    }
}
