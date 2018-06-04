package com.xxlai.gatewayservicedemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
@RestController
public class GatewayServiceDemoApplication {

	private static final Logger LOG = LoggerFactory
			.getLogger(GatewayServiceDemoApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceDemoApplication.class, args);
	}
	
	@Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }


	@RequestMapping("/hi")
	public String home() {
		LOG.info("gateway is being called");
		return "hi i'm gateway!";
	}
	
	@RequestMapping("/miya")
    public String info(){
        LOG.info("info is being called");
        return restTemplate.getForObject("http://localhost:8680/info",String.class);
    }


}
