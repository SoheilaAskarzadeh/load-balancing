package com.serviceconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/service/v1")
public class ConsumerController {

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @GetMapping("/consumers")
    public String printProducers(){

        ServiceInstance instance=loadBalancerClient.choose("service-producer");
        String baseUrl=instance.getUri().toString();
        baseUrl=baseUrl+"/service/v1/producers";
        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity responseEntity=restTemplate.exchange(baseUrl, HttpMethod.GET,getHeaders(),String.class);
       return responseEntity.getBody().toString()+" from port "+instance.getUri().getPort();

    }

    private static HttpEntity<?> getHeaders() {
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.set("accept", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(httpHeaders);
    }
}

