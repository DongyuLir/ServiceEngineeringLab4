package ynu.edu.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ynu.edu.entity.CommonResult;
import ynu.edu.entity.User;

@FeignClient("provider-server") //这启动类中的EnableReignClien相匹配使用
//这个是叫provider-server提供的服务的客户端,能够找到名叫provider-server的应用
public interface ServiceProviderService {//服务提供者的服务
    @GetMapping("/user/getUserById/{userId}")//注意这里的路径要写全
    CommonResult<User> getUserById(@PathVariable("userId") Integer userId);

}
