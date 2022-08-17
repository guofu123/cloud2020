package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;
    @Resource
    private DiscoveryClient discoveryClient;

    /**
     * 插入记录
     * @param payment
     * @return
     */
    @PostMapping(value = "/create")
    public CommonResult create(@RequestBody Payment payment){
        Integer result = paymentService.create(payment);
        log.info("****** 插入操作返回结果 *******");

        if (result>0){
            return new CommonResult(200, "插入数据库成功,serverPort: " + serverPort,result);
        }else {
            return new CommonResult(444, "插入数据库失败, serverPort: " + serverPort, null);
        }
    }

    @GetMapping("/get/{id}")
    public CommonResult get(@PathVariable Long id){
        Payment payment = paymentService.getPaymentById(id);

        log.info("*********11111 查询的结果   : " + payment);

        if (payment!=null){
            return new CommonResult(200, "查询数据库成功,serverPort："+serverPort, payment);
        }else {
            return new CommonResult(444, "查询数据库失败, serverPort: "+serverPort, null);
        }
    }
    /**
     * 发现服务
     */
    @GetMapping(value = "/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String elem : services){
            System.out.println(elem);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance elem : instances){
            System.out.println(elem.getServiceId() + "\t" + elem.getHost() + "\t" + elem.getPort() + "\t" + elem.getUri());
        }
        return this.discoveryClient;
    }

}
