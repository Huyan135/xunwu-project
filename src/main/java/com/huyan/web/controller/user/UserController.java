package com.huyan.web.controller.user;

import com.huyan.base.ApiResponse;
import com.huyan.base.LoginUserUtil;
import com.huyan.service.IUserService;
import com.huyan.service.ServiceResult;
import com.huyan.service.house.IHouseService;
import org.apache.http.HttpStatus;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.transform.Result;
import java.net.HttpCookie;

/**
 * @author 胡琰
 * @date 2019/4/14 17:26
 */
@Controller
public class UserController {

    @Autowired
    private IHouseService houseService;

    @Autowired
    private IUserService userService;

    @GetMapping("/user/login")
    public String loginPage(){
        return "user/login";
    }

    @GetMapping("/user/center")
    public String centerPage(){
        return "user/center";
    }

    @PostMapping(value = "api/user/info")
    @ResponseBody
    public ApiResponse updateUserInfo(@RequestParam(value = "profile") String profile,@RequestParam(value = "value") String value){
        if (value.isEmpty()){
            return ApiResponse.ofStatus(ApiResponse.Status.BAD_REQUEST);
        }
        if ("email".equals(profile) && !LoginUserUtil.checkEmail(value)){
            return ApiResponse.ofMessage(HttpStatus.SC_BAD_REQUEST,"不支持邮箱格式");


        }
        ServiceResult result = userService.modifyUserProfile(profile,value);
        if (result.isSuccess()){
            return ApiResponse.ofSuccess("");
        }else{
            return ApiResponse.ofMessage(HttpStatus.SC_BAD_REQUEST , result.getMessage());
        }

    }
    @PostMapping(value = "api/user/house/subscribe")
    @ResponseBody
    public ApiResponse subscribeHouse(@RequestParam(value = "house_id") Long houseId){
       ServiceResult result = houseService.addSubscribeOrder(houseId);
       if (result.isSuccess()){
           return ApiResponse.ofSuccess("");
       }else{
           return ApiResponse.ofMessage(HttpStatus.SC_BAD_REQUEST,result.getMessage());
       }
    }

}
