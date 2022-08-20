package com.atguifu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 请求支付服务使用自定义的负载均衡算法，需要创建在不被ComponentScan扫描到的包，保证该
 * 规则是只适用于请求支付服务的，否则会对所有的请求会生效
 */
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule(){
        // 随机算法
        return new RandomRule();
    }
}
