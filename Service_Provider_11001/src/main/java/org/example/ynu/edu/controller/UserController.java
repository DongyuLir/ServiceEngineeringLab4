package org.example.ynu.edu.controller;

import org.example.ynu.edu.entity.CommonResult;
import org.example.ynu.edu.entity.User;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/getUserById/{userId}")
    public CommonResult<User> getUserById(@PathVariable("userId") Integer userId){
        CommonResult<User> result = new CommonResult<>();
        Integer code = 200;
        String message = "success(11001)";
        //如果是连接数据库的话，就可能会出错。所以这里设置了一个try catch块.对返回的结果进行封装。
        //如果不这样做的话，出现问题的话，页面就会直接挂的、服务也会终止喔。
        try{
            User u = new User(userId, "小明", "123456");
            result.setResult(u);
        }
        catch(Exception e){
            code = 500;
            message = "failed";
        }
        result.setMessage(message);
        result.setCode(code);

        return result;
    }
}
