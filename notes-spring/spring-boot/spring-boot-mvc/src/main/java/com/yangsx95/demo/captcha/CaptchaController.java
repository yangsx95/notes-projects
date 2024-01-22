package com.yangsx95.demo.captcha;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.awt.image.BufferedImage;

/**
 * 图形验证码
 *
 * @author yangsx
 * @version 1.0
 * @date 2019/6/14
 */
@Controller
public class CaptchaController {

    @GetMapping(value = "/captcha",
            headers = {"Cache-Control=no-cache", "Pragma=no-cache", "Expires=0"},
            produces = {"image/png"})
    public BufferedImage captcha() {
        return null;
    }

}
