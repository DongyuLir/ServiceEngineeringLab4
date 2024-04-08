package ynu.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import ynu.edu.rule.CustomLoadBalancerConf;
import ynu.edu.rule.CustomThreeTimeLoadBalancerConf;
import ynu.edu.rule.CustomWeightLoadBalancerConf;

@SpringBootApplication
@EnableFeignClients //会在该应用中查找所有的 含有@FeignClient注解 的
@LoadBalancerClient(name = "provider-server")
public class ConsumerApplication16000 {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication16000.class,args);
    }
}
