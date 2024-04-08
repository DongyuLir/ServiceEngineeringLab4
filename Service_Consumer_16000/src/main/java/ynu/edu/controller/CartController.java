package ynu.edu.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.annotation.Resource;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ynu.edu.entity.CommonResult;
import ynu.edu.entity.User;
import ynu.edu.feign.ServiceProviderService;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
@Resource
private ServiceProviderService serviceProviderService;
    @GetMapping("/getCartById/{userId}")
    @CircuitBreaker(name="backendA", fallbackMethod = "getCartByIdDown")  //16000是B,在向A请求服务  这个控制器 配置熔断机制。 A来向B反馈，所以终止于A
    public CommonResult<User> getCartById(@PathVariable("userId") Integer userId){
        CommonResult<User> result = serviceProviderService.getUserById(userId);
        return result;
    }

    public CommonResult<User> getCartByIdDown(Integer userId, Throwable e){ //这个callback方法接受的参数首先需要有原方法 接收的参数. 同时还有一个异常 参数
        //可以根据产生的异常不同，可以设置不同的异常类型，Throwable是最广泛的，比如：SQLDataException（这个异常对应的返回的异常信息就是 ：请联系管理员，当前数据库异常
        e.printStackTrace();
        String message = "获取用户"+userId+"信息的服务当前被熔断，因此方法降级";
        System.out.println(message);
        CommonResult<User> result = new CommonResult<>(400, message, new User());
        return result;
    }
}
