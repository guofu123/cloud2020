package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentOpenFeignService {
    @GetMapping("/payment/get/{id}")
    CommonResult get(@PathVariable("id") Long id);

    /**
     * 生产者服务延迟测试
     * @return
     */
    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeOut();
}
