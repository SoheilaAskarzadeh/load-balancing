package com.serviceproducer.controller;

import com.serviceproducer.entity.Producer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service/v1")
public class ProducerController {

    @GetMapping("/producers")
    public String getProducers(){
        Producer producer=new Producer();
        producer.setName("producer1");
        producer.setPrice("1000");
        return producer.toString();
    }

}
