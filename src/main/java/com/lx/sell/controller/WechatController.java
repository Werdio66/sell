package com.lx.sell.controller;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


/**
 * @author Werdio丶
 * @since 2020-05-19 08:58:09
 */
@Slf4j
@RequestMapping("/wechat")
@Controller
public class WechatController {

    @Autowired
    private WxMpService wxMpService;

    @ResponseBody
    @GetMapping("/hello")
    public String hello(String echostr){
        log.info("【微信测试】echostr = {}", echostr);
        return echostr;
    }

    @GetMapping("/authorize")
    public String authorize(String returnUrl){
        log.info("【微信测试】returnUrl = {}", returnUrl);
        String url = "http://werdio.nat300.top/sell/wechat/userInfo";
        String redirectUrl = null;
        try {
            redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            log.error("【微信授权】 编码错误");
            e.printStackTrace();
        }
        log.info("【微信测试】redirectUrl = {}", redirectUrl);
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/userInfo")
    public String userInfo(String code, String state){
        log.info("【微信测试】 code = {}, state = {}", code, state);
        String openid = "";
        try {
            WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
            openid = wxMpOAuth2AccessToken.getOpenId();
            log.info("【微信测试】openid = {}", openid);

        } catch (WxErrorException e) {
            e.printStackTrace();
        }


        return "redirect:" + state + "?openid=" + openid;
    }
}
