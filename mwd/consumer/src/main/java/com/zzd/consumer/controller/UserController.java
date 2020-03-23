package com.zzd.consumer.controller;

import com.zzd.api.eunms.EntityStatus;
import com.zzd.api.domain.TUser;
import com.zzd.api.dto.ResponseResult;
import com.zzd.api.dto.UserDTO;
import com.zzd.api.eunms.UserRoleType;
import com.zzd.api.service.LoginService;
import com.zzd.api.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author
 * @date
 * @describe
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController{
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;
    @Resource
    private LoginService loginService;

    @RequestMapping(value = "selectUserByAccount",method = RequestMethod.GET)
    @ResponseBody
    public Object selectUserByAccount() {
        try{
            TUser user = userService.selectUserByAccount("123456", EntityStatus.Valid.getCode());
            return ResponseResult.build(user);
        }catch (Exception e){
            return ResponseResult.error(e.getMessage());
        }
    }


    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ResponseBody
    public Object login(@RequestBody UserDTO userDTO, HttpServletRequest request, HttpServletResponse response) {
        try{
            TUser user = userService.selectUserByAccount(userDTO.getUserAccount(), EntityStatus.Valid.getCode());
            if (user==null){
                return ResponseResult.error("用户不存在");
            }else if(!StringUtils.equals(user.getUserPassword(),userDTO.getUserPassword())){
                return ResponseResult.error("用户密码错误");
            }
            HttpSession session = request.getSession();
            session.setAttribute("userInfo",user);
            return ResponseResult.build(user);
        }catch (Exception e){
            return ResponseResult.error(e.getMessage());
        }
    }

    @RequestMapping(value = "loginOut",method = RequestMethod.POST)
    @ResponseBody
    public Object loginOut() {
        try{
            loginService.loginOut();
            return ResponseResult.ok();
        }catch (Exception e){
            return ResponseResult.error(e.getMessage());
        }
    }

    @RequestMapping(value = "getLoginInfo",method = RequestMethod.GET)
    @ResponseBody
    public Object getLoginInfo() {
        try{
            TUser user = loginService.getLoginInfo();
            return ResponseResult.build(user);
        }catch (Exception e){
            return ResponseResult.error(e.getMessage());
        }
    }

    @RequestMapping(value = "register",method = RequestMethod.POST)
    @ResponseBody
    public Object register(@RequestBody UserDTO userDTO) {
        try{
            if (StringUtils.equals(userDTO.getRoleType(), UserRoleType.Ordinary.getType())){
                userService.addUser(userDTO,"system");
            }else{

            }
            return ResponseResult.ok();
        }catch (Exception e){
            return ResponseResult.error(e.getMessage());
        }
    }

    @RequestMapping(value = "updateUserInfo",method = RequestMethod.POST)
    @ResponseBody
    public Object updateUserInfo(@RequestBody UserDTO userDTO) {
        try{
            if (StringUtils.isBlank(userDTO.getUserName())){
                ResponseResult.error("用户信息为空，更新失败");
            }
            String operator = getOperator();
            userService.updateUser(userDTO,operator);
            return ResponseResult.ok();
        }catch (Exception e){
            return ResponseResult.error(e.getMessage());
        }
    }




}
