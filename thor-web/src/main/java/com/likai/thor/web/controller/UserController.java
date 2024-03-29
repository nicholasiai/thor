package com.likai.thor.web.controller;

import com.likai.thor.dao.domain.UserInfoDo;
import com.likai.thor.service.bo.GatewayReqDto;
import com.likai.thor.service.gateway.UserGateway;
import com.likai.thor.service.user.UserManager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserManager userManager;
    @Resource
    private UserGateway userGateway;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String defaultLogin() {
        GatewayReqDto reqDto = new GatewayReqDto();
        reqDto.setUserId(124);
        System.out.println("controller开始");
        UserInfoDo userInfoDo = userGateway.gatUser(reqDto);
        System.out.println("controller结束");
        System.out.println(userInfoDo);
        return userInfoDo.toString();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 执行认证登陆
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            return "未知账户";
        } catch (IncorrectCredentialsException ice) {
            return "密码不正确";
        } catch (LockedAccountException lae) {
            return "账户已锁定";
        } catch (ExcessiveAttemptsException eae) {
            return "用户名或密码错误次数过多";
        } catch (AuthenticationException ae) {
            return "用户名或密码不正确！";
        } catch (Exception e){
            return "系统异常";
        }
        if (subject.isAuthenticated()) {
            return "登录成功";
        } else {
            token.clear();
            return "登录失败";
        }
    }
}
