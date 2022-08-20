package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.service.PaymentOpenFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/order")
public class OrderOpenFeignController {
    @Resource
    private PaymentOpenFeignService paymentOpenFeignService;

    @GetMapping("/get/openfeign/{id}")
    public CommonResult get(@PathVariable("id")Long id){
        return paymentOpenFeignService.get(id);
    }

    /**
     * 生产者服务延迟测试
     * @return
     */
    @GetMapping(value = "/feign/timeout")
    public String orderFeignTimeOut(){
        return paymentOpenFeignService.paymentFeignTimeOut();
    }
}
