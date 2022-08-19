package com.atguigu.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order/zk")
public class OrderZKController {

    @Resource
    private RestTemplate restTemplate;
    private static final String INVOKE_URL = "http://cloud-provider-payment";

    @GetMapping("/hello")
    public String hello(){
        String s = restTemplate.getForObject(INVOKE_URL + "/payment/zk/hello", String.class);
        System.out.printf("消费者调用支付服务（zookeeper）-->result:" + s);
        return s;
    }

}
