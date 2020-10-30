package com.hengxunda.springcloud.gateway.configuration;

import com.hengxunda.springcloud.gateway.filter.AuthenticationGatewayStrategyRouteFilter;
import com.hengxunda.springcloud.gateway.filter.GatewayStrategyRouteFilter;
import com.hengxunda.springcloud.service.common.redis.RedisHelper;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.netflix.ribbon.RibbonClientConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.reactive.HiddenHttpMethodFilter;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
@AutoConfigureBefore(RibbonClientConfiguration.class)
@ConditionalOnProperty(value = "spring.application.strategy.control.enabled", matchIfMissing = true)
public class GatewayStrategyAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(value = "spring.application.strategy.gateway.route.filter.enabled", matchIfMissing = true)
    public GatewayStrategyRouteFilter gatewayStrategyRouteFilter() {
        return new AuthenticationGatewayStrategyRouteFilter();
    }

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
                return super.filter(exchange, chain);
            }
        };
    }

    @Bean
    @ConditionalOnMissingBean
    public RedisHelper redisHelper() {
        return new RedisHelper();
    }
}