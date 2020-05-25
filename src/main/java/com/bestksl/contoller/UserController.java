package com.bestksl.contoller;

import com.bestksl.contoller.viewobject.UserVO;
import com.bestksl.error.BusinessException;
import com.bestksl.error.EmBusinessError;
import com.bestksl.response.CommonReturnType;
import com.bestksl.service.UserService;
import com.bestksl.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@Controller("user")
@RequestMapping("/user")
@RestController
public class UserController extends BaseController {
    @Autowired
    UserService userService;

    @GetMapping("/getotp")
    public CommonReturnType getOtp(@RequestParam(name = "telephone") String telephone) {
        //生成OTP验证码
        Random random = new Random();
        int randomInt = random.nextInt(99999) + 10000;
        String otpCode = String.valueOf(randomInt);

        //将OTP验证码和手机号关联

        //将OTP验证码发送, 通过OTP短信通道
    }

    @GetMapping("/get")
    public CommonReturnType getUser(@RequestParam("id") int id) throws BusinessException {
        UserModel userModel = userService.getUserById(id);
        if (userModel == null) {
            throw new BusinessException(EmBusinessError.User_NOT_EXIST);
        }
        // 将核心领域对象转换成user view 对象 供ui使用
        UserVO userVO = convertFromModel(userModel);
        return CommonReturnType.create(userVO);
    }


    private UserVO convertFromModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel, userVO);
        return userVO;
    }


}
