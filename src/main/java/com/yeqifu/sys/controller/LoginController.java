package com.yeqifu.sys.controller;


import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.ShearCaptcha;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yeqifu.common.ResultObj;
import com.yeqifu.sys.entity.User;
import com.yeqifu.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * <p>
 * 前端控制器
 * 登陆控制
 * </p>
 *
 * @author luoyi
 * @since 2019-11-18
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private IUserService userService;



    /**
     * 跳转到登陆页面
     * @return
     */
    @RequestMapping("toLogin")
    public String toLogin(){
        return "login";
    }

    /**
     * 完成登陆
     * @return
     */
    @RequestMapping("login")
    @ResponseBody
    public ResultObj login(String loginname, String pwd,String code, HttpSession session){
        Object codeSession = session.getAttribute("code");
        if (code!=null&&code.equals(codeSession)){
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("loginname",loginname);
            queryWrapper.eq("pwd",pwd);
            User user = userService.getOne(queryWrapper);
            if (null!=user){
                session.setAttribute("user",user);
                return new ResultObj(200,"登陆成功");
            }else {
                return new ResultObj(-1,"用户名或密码不正确");
            }
        }else {
            return new ResultObj(-1,"验证码错误");
        }
    }

    /**
     * 生成验证码
     */
    @RequestMapping("getCode")
    public void getCode(HttpSession session, HttpServletResponse response){
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(116,36,4,4);
        //将验证码存储到session中
        session.setAttribute("code",captcha.getCode());

        try {
            ServletOutputStream outputStream = response.getOutputStream();
            captcha.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

